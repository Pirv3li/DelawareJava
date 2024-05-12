package gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import domein.DomeinController;
import domein.Interface_Adres;
import domein.Interface_Bedrijf;
import domein.Interface_Leverancier;

public class BedrijvenController {

	@FXML
	private Label bedrijfNaam;

	@FXML
	private Label sector;

	@FXML
	private Label emailBedrijf;

	@FXML
	private Label iban;

	@FXML
	private Label btwNummer;

	@FXML
	private Label telefoonNummer;

	@FXML
	private Label gebruikerSinds;

	@FXML
	private Label straat;

	@FXML
	private Label stad;

	@FXML
	private Label isActief;

	@FXML
	private Label betaalMethodesLeverancier;

	@FXML
	private Label emailLeverancier;

	@FXML
	private Label leverancierNummer;

	@FXML
	private Label gebruikersnaamLeverancier;

	@FXML
	private TableView<Interface_Bedrijf> bedrijfTable;

	@FXML
	private TableColumn<Interface_Bedrijf, String> naamColumn;

	@FXML
	private TableColumn<Interface_Bedrijf, String> isActiefColumn;

	@FXML
	private Button switchBedrijf;

	@FXML
	private Button switchLeverancier;

	@FXML
	private GridPane bedrijfInformatie;

	@FXML
	private GridPane leverancierInformatie;

	@FXML
	private VBox informatieVBox;

	@FXML
	private VBox informatieVBox1;

	@FXML
	private ImageView logo;
	@FXML
	private ImageView delawareLogo;

	@FXML
	private Stage primaryStage;

	@FXML
	private FontAwesomeIconView btnRechts;

	@FXML
	private FontAwesomeIconView btnLinks;
	
	@FXML
	private Label lblPage;

	@FXML
	ComboBox<Integer> cbxAantal;

	private DomeinController controller;

	private int begin = 0;
	private int setAantal = 0;
	private int pageCounter = 1;

	public BedrijvenController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

//    public void start() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bedrijven.fxml"));
//            loader.setController(this);
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
//            Image image = new Image(url);
//            delawareLogo.setImage(image);
//            if (primaryStage != null) {
//                primaryStage.setScene(scene);
//                //primaryStage.setFullScreen(true);
//                primaryStage.centerOnScreen();
//
//                primaryStage.show();
//            } else {
//                System.err.println("PrimaryStage is null. Scene not set.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	public void initialize() {
		String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image = new Image(url);
		delawareLogo.setImage(image);
		naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
		isActiefColumn.setCellValueFactory(cellData -> {
			boolean isActief = cellData.getValue().isIsActief();
			String actiefStatus = isActief ? "Actief" : "Niet actief";
			return new SimpleStringProperty(actiefStatus);
		});

		cbxAantal.setValue(10);
		cbxAantal.getItems().addAll(10, 20, 30);
		bedrijfTable.setItems(getBedrijven(cbxAantal.getValue(), begin));
		lblPage.setText("" + pageCounter);
		bedrijfTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				informatieVBox1.setVisible(true);

				vulBedrijfDetailsTable(newSelection);
			}
		});

		btnRechts.setOnMouseClicked(e -> {
			informatieVBox1.setVisible(false);
			pageCounter++;
			lblPage.setText("" + pageCounter);

			begin += cbxAantal.getValue();
			Integer aantal = cbxAantal.getValue();
			if (begin > 0) {
				btnLinks.setVisible(true);}
			else {
				btnLinks.setVisible(false);
			}
			
			
			
			bedrijfTable.setItems(getBedrijven(aantal, begin));
			bedrijfTable.refresh();
			
			if(bedrijfTable.getItems().size() < cbxAantal.getValue()) {
                btnRechts.setVisible(false);}

		});

			btnLinks.setOnMouseClicked(e -> {
				informatieVBox1.setVisible(false);
				pageCounter--;
				lblPage.setText("" + pageCounter);

				begin -= cbxAantal.getValue();
				Integer aantal = cbxAantal.getValue();
				if (begin <= 0) {
					btnLinks.setVisible(false);
					begin = 0;
				}
				btnRechts.setVisible(true);
				bedrijfTable.setItems(getBedrijven(aantal, begin));
				bedrijfTable.refresh();

			});
			
			cbxAantal.setOnAction(e -> {
				informatieVBox1.setVisible(false);
				pageCounter = 1;
				begin = 0;
				
				Integer aantal = cbxAantal.getValue();
				bedrijfTable.setItems(getBedrijven(aantal, begin));
				bedrijfTable.refresh();
				

				btnLinks.setVisible(false);
				btnRechts.setVisible(true);
				if(bedrijfTable.getItems().size() < cbxAantal.getValue()) {
	                btnRechts.setVisible(false);}
			});
			
	}

	@FXML
	private void switchToBedrijfInformatie() {
		bedrijfInformatie.setVisible(true);
		leverancierInformatie.setVisible(false);
		switchInformatiePosition();
	}

	@FXML
	private void switchToLeverancierInformatie() {
		leverancierInformatie.setVisible(true);
		bedrijfInformatie.setVisible(false);
		switchInformatiePosition();
	}

	private void switchInformatiePosition() {
		informatieVBox.getChildren().remove(bedrijfInformatie);
		informatieVBox.getChildren().remove(leverancierInformatie);

		if (bedrijfInformatie.isVisible()) {
			informatieVBox.getChildren().add(0, bedrijfInformatie);
		} else if (leverancierInformatie.isVisible()) {
			informatieVBox.getChildren().add(0, leverancierInformatie);
		}
	}

	private ObservableList<Interface_Bedrijf> getBedrijven(int aantal, int begin) {
		ObservableList<Interface_Bedrijf> bedrijven = controller.getBedrijven(aantal, begin);
		
		return bedrijven;
	}

	private void vulBedrijfDetailsTable(Interface_Bedrijf bedrijf) {
		Interface_Adres adres = controller.getAdresByIdAdres(bedrijf.getIdAdres());

		if (bedrijf != null) {
			String url = bedrijf.getLogo();
			Image image = new Image(url);
			logo.setImage(image);
			bedrijfNaam.setText(bedrijf.getNaam());
			sector.setText(bedrijf.getSector());
			emailBedrijf.setText(bedrijf.getEmail());
			iban.setText(bedrijf.getIban());
			btwNummer.setText(bedrijf.getBtwNummer());
			telefoonNummer.setText(String.valueOf(bedrijf.getTelefoonnummer()));
			gebruikerSinds.setText(bedrijf.getGebruikerSinds().toString());
			straat.setText(adres.getStraat());
			stad.setText(adres.getStad());
			isActief.setText(bedrijf.isIsActief() ? "Actief" : "Niet actief");
			isActief.setTextFill(bedrijf.isIsActief() ? Color.GREEN : Color.RED);

			setupLeverancierInformatie(bedrijf);
		}
	}

	public void setupLeverancierInformatie(Interface_Bedrijf bedrijf) {
		Interface_Leverancier lever = controller.getLeverancierGegevensByIdBedrijf(bedrijf.getIdBedrijf());
		String leverancierNummer = lever.getLeveranciernummer();
		String gebruikersnaam = lever.getGebruikersnaam();
		String email = lever.getEmail();
		String betaalMethodes = lever.getBetaalMethodes();
		setLeverancierInformatie(leverancierNummer, gebruikersnaam, email, betaalMethodes);
	}

	public void setLeverancierInformatie(String leverancierNummer, String gebruikersnaam, String email,
			String betaalMethodes) {
		this.leverancierNummer.setText(leverancierNummer);
		this.gebruikersnaamLeverancier.setText(gebruikersnaam);
		this.emailLeverancier.setText(email);
		this.betaalMethodesLeverancier.setText(betaalMethodes);
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
	public void switchGoedKeuring(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/BedrijfWijziging.fxml"));
			GoedKeuringController goedKeuringController = new GoedKeuringController(primaryStage);
			goedKeuringController.setController(controller);
			loader.setController(goedKeuringController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
//			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
