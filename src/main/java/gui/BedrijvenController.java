package gui;

import javafx.collections.FXCollections;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import domein.Adres;
import domein.Bedrijf;
import domein.DomeinController;
import domein.Leverancier;

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
    private TableView<Bedrijf> bedrijfTable;

    @FXML
    private TableColumn<Bedrijf, String> naamColumn;

    @FXML
    private TableColumn<Bedrijf, String> isActiefColumn;

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
    private ImageView logo;



    private Stage primaryStage;

    private DomeinController controller;


    public BedrijvenController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bedrijven.fxml"));
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
        naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        isActiefColumn.setCellValueFactory(cellData -> {
            boolean isActief = cellData.getValue().isIsActief();
            String actiefStatus = isActief ? "Actief" : "Niet actief";
            return new SimpleStringProperty(actiefStatus);
        });

        bedrijfTable.setItems(getBedrijven());
        
        
        bedrijfTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vulBedrijfDetailsTable(newSelection);
            }
        });
    }
    
    @FXML
    private void switchToBedrijf() {
        bedrijfInformatie.setVisible(true);
        leverancierInformatie.setVisible(false);
        switchInformatiePosition();
    }

    @FXML
    private void switchToLeverancier() {
        leverancierInformatie.setVisible(true);
        bedrijfInformatie.setVisible(false);
        switchInformatiePosition();
    }
    
    private void switchInformatiePosition() {
        // Remove existing panes if present
        informatieVBox.getChildren().remove(bedrijfInformatie);
        informatieVBox.getChildren().remove(leverancierInformatie);

        // Add the panes in the new positions
        if (bedrijfInformatie.isVisible()) {
            informatieVBox.getChildren().add(0, bedrijfInformatie);
        } else if (leverancierInformatie.isVisible()) {
            informatieVBox.getChildren().add(0, leverancierInformatie);
        }
    }


    
    private ObservableList<Bedrijf> getBedrijven() {
        List<Bedrijf> bedrijven = controller.getBedrijven();
        return FXCollections.observableArrayList(bedrijven);
    }


    private void vulBedrijfDetailsTable(Bedrijf bedrijf) {
        Adres adres = controller.getAdresByIdAdres(bedrijf.getIdAdres());
        
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
    
    public void setupLeverancierInformatie(Bedrijf bedrijf) {
        Leverancier lever = controller.getLeverancierGegevensByIdBedrijf(bedrijf.getIdBedrijf());
        String leverancierNummer = lever.getLeveranciernummer();
        String gebruikersnaam = lever.getGebruikersnaam(); 
        String email = lever.getEmail(); 
        String betaalMethodes = lever.getBetaalMethodes();
        setLeverancierInformatie(leverancierNummer, gebruikersnaam, email, betaalMethodes);
    }

    
    public void setLeverancierInformatie(String leverancierNummer, String gebruikersnaam, String email, String betaalMethodes) {
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
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
            AanmeldenController aanmeldenController = new AanmeldenController(controller, new Stage());
            loader.setController(aanmeldenController);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.setTitle("Login");
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
