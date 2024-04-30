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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profiel.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
            Image image = new Image(url);
			delawareLogo.setImage(image);
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
            List<String> selectedPaymentMethods = new ArrayList<>(betaalMethodes.getSelectionModel().getSelectedItems());

            lever.setBetaalMethodes(selectedPaymentMethods.toArray(new String[0]));
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        KlantenController klantenController = new KlantenController(new Stage());
        klantenController.setController(controller);
        klantenController.start();
    }
    
    @FXML
    public void switchBestellingPagina(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BestellingController bestellingController = new BestellingController(stage);
        bestellingController.setController(controller);
        bestellingController.start();
    }
}
