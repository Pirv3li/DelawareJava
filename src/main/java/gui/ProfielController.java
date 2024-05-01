package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import domein.Adres;
import domein.Bedrijf;
import domein.DomeinController;
import domein.Leverancier;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProfielController {

    @FXML
    private Button notificaties;

    @FXML
    private Button bestellingen;

    @FXML
    private Button profiel;

    @FXML
    private Button klanten;

    @FXML
    private Button logout;

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
    private Label leverancierNummer;

    @FXML
    private Label gebruikersNaam;

    @FXML
    private Label leverancierEmail;

    @FXML
    private ListView<String> betaalMethodes;
    
    private final List<String> allPaymentMethods = Arrays.asList("CreditCard", "PayPal", "Bancontact", "Klarna");

    @FXML
    private ImageView leverancierLogo;

    @FXML
    private Button opslaanButton;
    
    @FXML
    private ImageView delawareLogo;
    
    
	private Stage primaryStage;
	private DomeinController controller;
    
    public ProfielController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profiel.fxml"));
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
    	Leverancier lever = controller.getLeverancier();;
    	Bedrijf bedrijf = lever.getBedrijf();
    	Adres adres = controller.getAdresByIdAdres(bedrijf.getIdAdres());
    	String url = bedrijf.getLogo();
        Image image = new Image(url);
        leverancierLogo.setImage(image);
    	bedrijfNaam.setText(bedrijf.getNaam());;
        sector.setText(bedrijf.getSector());
        emailBedrijf.setText(bedrijf.getEmail());
        iban.setText(bedrijf.getIban());
        btwNummer.setText(bedrijf.getBtwNummer());
        telefoonNummer.setText(String.valueOf(bedrijf.getTelefoonnummer()));
        gebruikerSinds.setText(bedrijf.getGebruikerSinds().toString());
        straat.setText(adres.getStraat());
        stad.setText(adres.getStad());
        leverancierNummer.setText(lever.getLeveranciernummer());
        gebruikersNaam.setText(lever.getGebruikersnaam());
        leverancierEmail.setText(lever.getEmail());
        
        betaalMethodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            opslaanButton.setVisible(true);
        });

        opslaanButton.setOnAction(event -> {
        	String selectedPaymentMethods = betaalMethodes.getSelectionModel().getSelectedItems().toString();

            lever.setBetaalMethodes(selectedPaymentMethods);
            System.out.println(lever.getBetaalMethodes());
            controller.updateLeverancier(lever);
           
            betaalMethodes.getItems().clear();
            betaalMethodes.getItems().addAll(selectedPaymentMethods);
            
            opslaanButton.setVisible(false);
        });

        betaalMethodes.getItems().addAll(allPaymentMethods);
        List<String> usedPaymentMethods = Arrays.asList(lever.getBetaalMethodes());
        
        for (String paymentMethod : allPaymentMethods) {
            if (usedPaymentMethods.contains(paymentMethod)) {
                betaalMethodes.getItems().stream()
                    .filter(item -> item.equals(paymentMethod))
                    .findFirst()
                    .ifPresent(item -> betaalMethodes.setStyle("-fx-text-fill: green;"));
            }
        }
        
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
			System.out.println("fail");;
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
	        primaryStage.show();
		} catch (Exception e) {
			System.out.println("fail");;
		}
    }
}
