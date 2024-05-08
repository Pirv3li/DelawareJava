package gui;

import java.util.ArrayList;
import java.util.List;

import domein.Bedrijf;
import domein.DomeinController;
import domein.Interface_Adres;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_BestellingDetails;
import domein.Interface_GoedKeuringLeverancier;
import domein.Interface_Leverancier;
import domein.Interface_Product;
import domein.ProductEnDetailsGecombineerd;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GoedKeuringController {

	private DomeinController controller;
	private Stage primaryStage;

	@FXML
	private ImageView delawareLogo;
	
	


	
	
	
	@FXML
	private VBox informatieVBox;
	@FXML
	private TableView<Interface_GoedKeuringLeverancier> goedKeuringTable;

	@FXML
	private TableColumn<Interface_GoedKeuringLeverancier, String> NummerColumn;

	@FXML
	private TableColumn<Interface_GoedKeuringLeverancier, String> SoortColumn;

	@FXML
	private TableColumn<Interface_GoedKeuringLeverancier, String> gebruikersnaamColumn;

	@FXML
	private TableColumn<Interface_GoedKeuringLeverancier, String> statusColumn;

	
	@FXML
	private Label vGebruikersnaam;
	
	@FXML
	private Label aGebruikersnaam;
	
	@FXML
	private Label vEmail;
	
	@FXML
	private Label aEmail;
	
	@FXML
	private Label vIban;

	@FXML
	private Label vBtw;

	@FXML
	private Label vTelefoon;

	@FXML
	private Label vSector;

	@FXML
	private Label vStad;

	@FXML
	private Label vPostcode;

	@FXML
	private Label vStraat;

	@FXML
	private Label vNummer;
	
	@FXML
	private Label aIban;

	@FXML
	private Label aBtw;

	@FXML
	private Label aTelefoon;

	@FXML
	private Label aSector;

	@FXML
	private Label aStad;

	@FXML
	private Label aPostcode;

	@FXML
	private Label aStraat;

	@FXML
	private Label aNummer;
	
	@FXML
	private Button btnAfwijzen;
	
	@FXML
	private Button btnAkkoord;
	
	
	
	
	
	
	
	public GoedKeuringController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	public void initialize() {
		String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image = new Image(url);
		delawareLogo.setImage(image);

		NummerColumn.setCellValueFactory(new PropertyValueFactory<>("leverancierNummer"));
		SoortColumn.setCellValueFactory(new PropertyValueFactory<>("roles"));
		gebruikersnaamColumn.setCellValueFactory(new PropertyValueFactory<>("gebruikersnaam"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("afgehandeld"));

		// OPTIES
		// in behandeling, goedgekeurd, afgekeurd
		goedKeuringTable.setItems(controller.getGoedKeuringen("in behandeling"));
		ObservableList<Interface_GoedKeuringLeverancier> goedKeuringen = controller.getGoedKeuringen("afgekeurd");



		goedKeuringTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	informatieVBox.setVisible(true);
            	
            	vulBestellingDetailsTable(newSelection);
            }
        });
		
		
		btnAfwijzen.setOnAction(e -> {
			System.out.println("afgekeurd");
			controller.updateGoedkeuringLeverancier((""+goedKeuringTable.getSelectionModel().getSelectedItem().getidGoedkeuringLeverancier()),"afgekeurd");
			
		});
		
		btnAkkoord.setOnAction(e -> {
			
			
		});
	}
	
	private void vulBestellingDetailsTable(Interface_GoedKeuringLeverancier goedkeuringLeverancier) {
		
		
		aGebruikersnaam.setText(goedkeuringLeverancier.getGebruikersnaam());
		aEmail.setText(goedkeuringLeverancier.getEmail());
		aIban.setText(goedkeuringLeverancier.getIban());
		aBtw.setText(goedkeuringLeverancier.getBtwNummer());
		aTelefoon.setText(goedkeuringLeverancier.getTelefoonnummer());
		aSector.setText(goedkeuringLeverancier.getSector());
		aStad.setText(goedkeuringLeverancier.getStad());
		aPostcode.setText(goedkeuringLeverancier.getPostcode());
		aStraat.setText(goedkeuringLeverancier.getStraat());
		aNummer.setText(goedkeuringLeverancier.getNummer());
		
		Interface_Leverancier l = controller.getLeverancierById(Integer.valueOf(goedkeuringLeverancier.getIdLeverancier()));
		Interface_Bedrijf b = l.getBedrijf();
		
		
		Interface_Adres a = controller.getAdresByIdAdres(b.getIdAdres());
		
		
		vGebruikersnaam.setText(l.getGebruikersnaam());

		vEmail.setText(l.getEmail());
		vIban.setText(b.getIban());
		vBtw.setText(b.getBtwNummer());
		vTelefoon.setText(""+b.getTelefoonnummer());
		vSector.setText(b.getSector());
		String str = a.getStad();
		vStad.setText(str.substring(0, str.indexOf(',')).trim());
		vPostcode.setText(""+a.getPostcode());
		String strS = a.getStraat();
		vStraat.setText(strS.substring(0, strS.indexOf(' ')).trim());
		vNummer.setText(""+a.getNummer());
		
	    compareAndSetColor(aGebruikersnaam, vGebruikersnaam);
	    compareAndSetColor(aEmail, vEmail);
	    compareAndSetColor(aIban, vIban);
	    compareAndSetColor(aBtw, vBtw);
	    compareAndSetColor(aTelefoon, vTelefoon);
	    compareAndSetColor(aSector, vSector);
	    compareAndSetColor(aStad, vStad);
	    compareAndSetColor(aPostcode, vPostcode);
	    compareAndSetColor(aStraat, vStraat);
	    compareAndSetColor(aNummer, vNummer);
		
	}
	
	private void compareAndSetColor(Label aLabel, Label vLabel) {
	    if (!aLabel.getText().equals(vLabel.getText())) {
	        aLabel.setStyle("-fx-text-fill: red;");
	    } else {
	        aLabel.setStyle("-fx-text-fill: black;");
	    }}
	  
	
	

	
	
	

	@FXML
	public void uitloggen(ActionEvent event) {
		controller.uitloggen();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		AanmeldenController aanmeldenController = new AanmeldenController(controller, new Stage());
		aanmeldenController.start();
	}

	@FXML
	public void switchBedrijven(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bedrijven.fxml"));
			BedrijvenController bedrijvenController = new BedrijvenController(primaryStage);
			bedrijvenController.setController(controller);
			loader.setController(bedrijvenController);
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
