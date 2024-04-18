package gui;

import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class BestellingController {

    private DomeinController controller;
    private Stage primaryStage;

    @FXML
    private TableView<Bestelling> bestellingTable;

    @FXML
    private TableView<BestellingDetails> productTable;

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
    private TableColumn<BestellingDetails, Double> totaalPrijsColumn;

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
        bedragColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));
        orderstatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        betalingstatusColumn.setCellValueFactory(new PropertyValueFactory<>("betalingStatus"));

        productNaamColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        stukprijsColumn.setCellValueFactory(new PropertyValueFactory<>("eenheidsPrijs"));
        btwPrijsColumn.setCellValueFactory(new PropertyValueFactory<>("btwTarief"));
        aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));
        totaalPrijsColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));

        bestellingTable.setItems(getBestellingen());

        bestellingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vulBestellingDetailsTable(newSelection);
            }
        });
    }

    private ObservableList<Bestelling> getBestellingen() {
        List<Bestelling> bestellingen = controller.FindbestellingenByLeverancierofKlant();
        return FXCollections.observableArrayList(bestellingen);
    }

    public void setController(DomeinController controller) {
        this.controller = controller;
    }

    private void vulBestellingDetailsTable(Bestelling bestelling) {
        List<BestellingDetails> details = controller.getBestellingDetails(bestelling);
        productTable.setItems(FXCollections.observableArrayList(details));
        double totaal = details.stream().mapToDouble(BestellingDetails::getTotaalPrijs).sum();
        totaalProductenLabel.setText(String.format("%.2f", totaal));
    }
}