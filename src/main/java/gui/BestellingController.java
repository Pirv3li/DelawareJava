package gui;

import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import domein.Notificatie;
import domein.Product;
import domein.ProductEnDetailsGecombineerd;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BestellingController {

	private DomeinController controller;
	private Stage primaryStage;

	@FXML
	private TableView<Bestelling> bestellingTable;

	@FXML
	private TableView<ProductEnDetailsGecombineerd> bestellingDetailsTable;

	@FXML
	private Label totaalProductenLabel;

	@FXML
	private TableColumn<Bestelling, String> orderidColumn;

	@FXML
	private TableColumn<Bestelling, String> datumColumn;

	@FXML
	private TableColumn<Bestelling, String> orderstatusColumn;

	@FXML
	private TableColumn<Bestelling, String> betalingstatusColumn;

	@FXML
	private TableColumn<BestellingDetails, String> productNaamColumn;

	@FXML
	private TableColumn<BestellingDetails, Double> stukprijsColumn;

	@FXML
	private TableColumn<BestellingDetails, Double> btwPrijsColumn;

	@FXML
	private TableColumn<BestellingDetails, Integer> aantalColumn;

	@FXML
	private TableColumn<BestellingDetails, Double> totaalPrijsPerProductColumn;

	@FXML
	private ImageView delawareLogo;

	@FXML
	private Button notificaties;

	@FXML
	private Button bestellingen;

	@FXML
	private Button profiel;

	@FXML
	private Button klanten;

	@FXML
	private Button betalingsherinnering;

	@FXML
	private VBox BestellingenRechts;

	public BestellingController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void start() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/bestellingen.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			if (primaryStage != null) {
				primaryStage.setScene(scene);
				// primaryStage.setFullScreen(true);

				Platform.runLater(() -> {
					primaryStage.centerOnScreen();
					primaryStage.show();
				});
			} else {
				System.err.println("PrimaryStage is null. Scene not set.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image1 = new Image(url1);
		delawareLogo.setImage(image1);
		orderidColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		orderstatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		betalingstatusColumn.setCellValueFactory(new PropertyValueFactory<>("betalingStatus"));
		productNaamColumn.setCellValueFactory(new PropertyValueFactory<>("productNaam"));
		stukprijsColumn.setCellValueFactory(new PropertyValueFactory<>("eenheidsPrijs"));
		btwPrijsColumn.setCellValueFactory(new PropertyValueFactory<>("btwTarief"));
		aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));
		totaalPrijsPerProductColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));

		bestellingTable.setItems(getBestellingen());

		bestellingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				BestellingenRechts.setVisible(true);
				vulBestellingDetailsTable(newSelection);
				isbetaald(newSelection);
				betalingsherinnering.setOnAction(stuurBetalingsherinnering(newSelection));
			}

		});

	}

	private void isbetaald(Bestelling newSelection) {
		if (newSelection.getBetalingStatus().equals("Betaald")) {
			betalingsherinnering.setVisible(false);
		} else {
			betalingsherinnering.setVisible(true);
		}
	}

	private ObservableList<Bestelling> getBestellingen() {
		List<Bestelling> bestellingen = controller.FindbestellingenByLeverancier();
		return FXCollections.observableArrayList(bestellingen);
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	private void vulBestellingDetailsTable(Bestelling bestelling) {
		if (bestelling != null) {
			List<BestellingDetails> details = controller.getBestellingDetails(bestelling);
			List<ProductEnDetailsGecombineerd> gecombineerdeData = new ArrayList<>();

			for (BestellingDetails bestellingDetails : details) {
				Product product = controller.getProductByProductId(bestellingDetails);
				gecombineerdeData.add(new ProductEnDetailsGecombineerd(product.getNaam(),
						bestellingDetails.getEenheidsPrijs(), product.getBtwTarief(), product.getAantal()));

			}

			bestellingDetailsTable.setItems(FXCollections.observableArrayList(gecombineerdeData));
			double totaal = details.stream().mapToDouble(BestellingDetails::getTotaalPrijs).sum();
			totaalProductenLabel.setText(String.format("€ %.2f", totaal));
		}
	}

	public EventHandler<ActionEvent> stuurBetalingsherinnering(Bestelling bestelling) {
		return event -> {
			controller.maakNotificatie(bestelling);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Betallings herinnering");
			alert.setHeaderText("Betallings herinnering");
			alert.setContentText("Betallingsherinnering is verzonden");
			alert.showAndWait();

		};
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
	public void switchKlantenPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Klanten.fxml"));
			KlantenController klantenController = new KlantenController(primaryStage);
			klantenController.setController(controller);
			loader.setController(klantenController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("fail");
			;
		}

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
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("fail");
			;
		}
	}

}