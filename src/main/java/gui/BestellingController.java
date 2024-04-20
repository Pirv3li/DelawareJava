	package gui;

import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import domein.Product;
import domein.ProductEnDetailsGecombineerd;
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
    private TableColumn<Bestelling, Double> bedragColumn;

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
    private Button bedrijven;
    
    
    
    @FXML
    private void handleBedrijvenButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bedrijven.fxml"));
            BedrijvenController bedrijvenController = new BedrijvenController(primaryStage);
            bedrijvenController.setController(controller);
            loader.setController(bedrijvenController);
            Parent root = loader.load();
            bedrijvenController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BestellingController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bestellingen.fxml"));
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

    public void initialize() {
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
                vulBestellingDetailsTable(newSelection);
            }
        });
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
                gecombineerdeData.add(new ProductEnDetailsGecombineerd(
                    product.getNaam(),
                    bestellingDetails.getEenheidsPrijs(),
                    product.getBtwTarief(),
                    product.getAantal()
                ));
               
            }
            
            for (ProductEnDetailsGecombineerd productEnDetailsGecombineerd : gecombineerdeData) {
				System.out.println(productEnDetailsGecombineerd.getTotaalPrijs()); 
				System.out.println();
			}
            bestellingDetailsTable.setItems(FXCollections.observableArrayList(gecombineerdeData));
            double totaal = details.stream().mapToDouble(BestellingDetails::getTotaalPrijs).sum();
            totaalProductenLabel.setText(String.format("%.2f", totaal));
        }
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