package gui;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import domein.DomeinController;
import domein.Interface_Adres;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_Klant;

public class KlantenController {

	@FXML
	private Label klantNaam;

	@FXML
	private Label emailKlant;

	@FXML
	private Label telefoonNummer;



	@FXML
	private Label straat;

	@FXML
	private Label stad;

	@FXML
	private Button bedrijven;

	@FXML
	private Button notificaties;

	@FXML
	private Button betalingStatus;

	@FXML
	private Button bestellingen;

	@FXML
	private Button profiel;

	@FXML
	private Button klanten;

	@FXML
	private Button logout;

	@FXML
	private Button klantSwitch;

	@FXML
	private Button bestellingenSwitch;

	@FXML
	private FontAwesomeIconView BELL;

	@FXML
	private FontAwesomeIconView USER;

	@FXML
	private FontAwesomeIconView USERS;

	@FXML
	private FontAwesomeIconView LIST;

	@FXML
	private TableView<Interface_Klant> KlantenTable;

	@FXML
	private TableView<Interface_Bestelling> bestellingenView;

	@FXML
	private TableColumn<Interface_Klant, String> klantNaamColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> aantalBestellingenColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderIDColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> datumColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> bedragColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderStatusColumn;

	@FXML
	private TableColumn<Interface_Bestelling, Boolean> betalingStatusColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> totaleBestellingen;

	@FXML
	private GridPane KlantView;

	private Stage primaryStage;

	@FXML
	private VBox KlantRechts;
	@FXML
	private VBox informatieVBox;
	@FXML
	private ImageView logo;
	@FXML
	private ImageView delawareLogo;

	private DomeinController controller;
	
	public static String convertToList(String input) {
		// Remove brackets and split by comma
		String[] tokens = input.replaceAll("\\[|\\]", "").split(", ");

		// Initialize list to store strings
		List<String> list = new ArrayList<>();

		// Add each token to the list
		for (String token : tokens) {
			list.add(token);
		}

		// Join the list into a single string with each item separated by a comma
		String result = String.join(", ", list);
		return result;
	}

	public KlantenController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void start() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Klanten.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			if (primaryStage != null) {
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} else {
				System.err.println("PrimaryStage is null. Scene not set.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	public void initialize() {
		String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image1 = new Image(url1);
		delawareLogo.setImage(image1);
		klantNaamColumn.setCellValueFactory(new PropertyValueFactory<>("gebruikersnaam"));

		KlantenTable.setItems(getKlanten());

		aantalBestellingenColumn.setCellValueFactory(new PropertyValueFactory<>("aantalBestellingen"));

		totaleBestellingen.setCellValueFactory(new PropertyValueFactory<>("totaalBestellingen"));

		KlantenTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				KlantRechts.setVisible(true);
				vulKlantDetailsTable(newSelection);
				betalingStatus.setVisible(false);

			}
		});

		bestellingenView.setOnMouseClicked(event -> {
			if (bestellingenView.getSelectionModel().getSelectedItem() != null) {
				Interface_Bestelling selectedOrder = bestellingenView.getSelectionModel().getSelectedItem();
				isbetaald(selectedOrder);
			}
		});

		
		
		betalingStatus.setOnAction(event -> {
			if (bestellingenView.getSelectionModel().getSelectedItem() != null) {
				Interface_Bestelling selectedOrder = bestellingenView.getSelectionModel().getSelectedItem();
				String idOrder = selectedOrder.getIdOrder();

				controller.veranderStatusOrder(idOrder);

				// Refresh the TableViews
				
				KlantenTable.refresh();
				bestellingenView.refresh();
				
			
			}
		});
		

	}
		


	@FXML
	private void switchToKlant() {
		KlantView.setVisible(true);
		bestellingenView.setVisible(false);
		switchInformatiePosition();
	}

	@FXML
	private void switchToBestellingen() {
		bestellingenView.setVisible(true);
		KlantView.setVisible(false);
		switchInformatiePosition();
	}

	private void switchInformatiePosition() {
		informatieVBox.getChildren().remove(KlantView);
		informatieVBox.getChildren().remove(bestellingenView);

		if (KlantView.isVisible()) {
			informatieVBox.getChildren().add(0, KlantView);
		} else if (bestellingenView.isVisible()) {
			informatieVBox.getChildren().add(0, bestellingenView);
		}
	}

	private ObservableList<Interface_Klant> getKlanten() {

		return controller.getKlantenByLeverancierId();
	}

	private void isbetaald(Interface_Bestelling newSelection) {
		if (newSelection.getBetalingStatus()) {
			betalingStatus.setVisible(false);
		} else {
			betalingStatus.setVisible(true);
		}
	}

	private void vulKlantDetailsTable(Interface_Klant klant) {
		Interface_Bedrijf bedrijf = controller.getBedrijfByKlant(klant);
		String telNr = String.valueOf(bedrijf.getTelefoonnummer());
		Interface_Adres adres = controller.getAdresByIdAdres(bedrijf.getIdAdres());

		if (klant != null) {
			String url = bedrijf.getLogo();
			Image image = new Image(url);
			logo.setImage(image);
			klantNaam.setText(klant.getGebruikersnaam());
			emailKlant.setText(klant.getEmail());
			telefoonNummer.setText(telNr);
			straat.setText(adres.getStraat());
			stad.setText(adres.getStad());

			setupKlantBestellingen(klant);
		}
	}

	public void setupKlantBestellingen(Interface_Klant klant) {
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("IdOrder"));
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("Datum"));
		bedragColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));
		orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
		betalingStatusColumn.setCellValueFactory(cellData -> {
			BooleanProperty betalingStatusProperty = cellData.getValue().betalingStatusProperty();
		    return betalingStatusProperty.asObject();
		});

		bestellingenView.setItems(getBestellingen(klant));
		bestellingenView.getItems().forEach(item -> {
		    item.betalingStatusProperty().addListener((observable, oldValue, newValue) -> {
		    	System.out.println("verandering opgemerkt");
		        bestellingenView.refresh();
		    });
		});
	}

	private ObservableList<Interface_Bestelling> getBestellingen(Interface_Klant klant) {
		
		return controller.findBestellingenByKlant(klant);
	}

	@FXML
	public void uitloggen(ActionEvent event) {
		controller.uitloggen();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		AanmeldenController aanmeldenController = new AanmeldenController(controller, new Stage());
		aanmeldenController.start();
	}

	@FXML
	public void switchProfielPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profiel.fxml"));
			ProfielController profielController = new ProfielController(primaryStage);
			profielController.setController(controller);
			loader.setController(profielController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
//			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("fail");
			;
		}
	}

	@FXML
	public void switchBestellingPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bestellingen.fxml"));
			BestellingController bestellingController = new BestellingController(primaryStage);
			bestellingController.setController(controller);
			loader.setController(bestellingController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
//			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("fail");
			;
		}
	}

}