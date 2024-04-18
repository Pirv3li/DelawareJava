package gui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class BestellingController {


    private DomeinController controller;

    private Stage primaryStage;
    
    @FXML
    private Button bedrijven;

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
    private Label orderid;

    @FXML
    private Label datum;

    @FXML
    private Label bedrag;

    @FXML
    private Label orderstatus;

    @FXML
    private Label betalingstatus;

    @FXML
    private Label orderid1;

    @FXML
    private Label datum1;

    @FXML
    private Label bedrag1;

    @FXML
    private Label orderstatus1;

    @FXML
    private Label betalingstatus1;

    @FXML
    private Label productNaam;

    @FXML
    private Label stukprijs;

    @FXML
    private Label btwPrijs;

    @FXML
    private Label aantal;

    @FXML
    private Label totaalPrijs;

    @FXML
    private Label totaal;
    
    @FXML
    private VBox bestellingList;
    
    @FXML HBox productenList;

    public BestellingController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    
    
    public void initialize() {
        List<Bestelling> bestellingen = controller.FindbestellingenByLeverancierofKlant();

        bestellingList.getChildren().clear();
        productenList.getChildren().clear();
        for (Bestelling bestelling : bestellingen) {
            HBox bestellingBox = new HBox();

            bestellingBox.setStyle("-fx-border-color: #D3D3D3;");
            bestellingBox.setPrefHeight(38.0);
            bestellingBox.setPrefWidth(600.0);
            bestellingBox.setSpacing(10);
            bestellingBox.setCursor(Cursor.HAND); // Add pointer cursor

            Label orderIdLabel = new Label("" + bestelling.getIdOrder());
            orderIdLabel.setAlignment(Pos.CENTER);
            orderIdLabel.setPrefHeight(40.0);
            orderIdLabel.setPrefWidth(120.0);

            Label datumLabel = new Label("" + bestelling.getDatum());
            datumLabel.setAlignment(Pos.CENTER);
            datumLabel.setPrefHeight(40.0);
            datumLabel.setPrefWidth(135.0);

            Label bedragLabel = new Label("" + bestelling.getTotaalPrijs());
            bedragLabel.setAlignment(Pos.CENTER);
            bedragLabel.setPrefHeight(40.0);
            bedragLabel.setPrefWidth(140.0);

            Label orderStatusLabel = new Label("" + bestelling.getOrderStatus());
            orderStatusLabel.setAlignment(Pos.CENTER);
            orderStatusLabel.setPrefHeight(40.0);
            orderStatusLabel.setPrefWidth(180.0);

            Label betalingStatusLabel = new Label("" + bestelling.getBetalingStatus());
            betalingStatusLabel.setAlignment(Pos.CENTER);
            betalingStatusLabel.setPrefHeight(40.0);
            betalingStatusLabel.setPrefWidth(180.0);

            orderIdLabel.setAlignment(Pos.CENTER);
            datumLabel.setAlignment(Pos.CENTER);
            bedragLabel.setAlignment(Pos.CENTER);
            orderStatusLabel.setAlignment(Pos.CENTER);
            betalingStatusLabel.setAlignment(Pos.CENTER);

            bestellingBox.setOnMouseClicked(event -> {
                orderid1.setText("" + bestelling.getIdOrder());
                datum1.setText("" + bestelling.getDatum());
                bedrag1.setText(String.valueOf(bestelling.getTotaalPrijs()));
                orderstatus1.setText("" + bestelling.getOrderStatus());
                betalingstatus1.setText("" + bestelling.getBetalingStatus());


                List<BestellingDetails> details = controller.getBestellingDetails(bestelling);
                for (BestellingDetails detail : details) {
                	System.out.println(detail.getProductName());
                	System.out.println("volgende:");
                    HBox productBox = new HBox();
                    productBox.setPrefWidth(596.0);
                    productBox.setSpacing(10.0);

                    Label productNameLabel = new Label(detail.getProductName());
                    productNameLabel.setAlignment(Pos.CENTER);
                    productNameLabel.setPrefSize(150.0, 68.0);
                    productNameLabel.setMinWidth(150);
                    productNameLabel.setMinWidth(150);
                    productNameLabel.setMaxWidth(150);
                    productNameLabel.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 0px 1px 0px 0px;");
                    productNameLabel.setWrapText(true);

                    Label stukprijsLabel = new Label(String.valueOf(detail.getEenheidsPrijs()));
                    stukprijsLabel.setAlignment(Pos.CENTER);
                    stukprijsLabel.setPrefSize(150.0, 68.0);
                    stukprijsLabel.setMinWidth(150);
                    stukprijsLabel.setMinWidth(150);
                    stukprijsLabel.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 0px 1px 0px 0px;");

                    Label btwPrijsLabel = new Label(String.valueOf(detail.getBtwtarief()));
                    btwPrijsLabel.setAlignment(Pos.CENTER);
                    btwPrijsLabel.setPrefSize(150.0, 68.0);
                    btwPrijsLabel.setMinWidth(150);
                    btwPrijsLabel.setMinWidth(150);
                    btwPrijsLabel.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 0px 1px 0px 0px;");

                    Label aantalLabel = new Label(String.valueOf(detail.getAantal()));
                    aantalLabel.setAlignment(Pos.CENTER);
                    aantalLabel.setPrefSize(150.0, 68.0);
                    aantalLabel.setMinWidth(150);
                    aantalLabel.setMinWidth(150);
                    aantalLabel.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 0px 1px 0px 0px;");

                    Label totaalPrijsLabel = new Label(String.valueOf(detail.getTotaalPrijs()));
                    totaalPrijsLabel.setAlignment(Pos.CENTER);
                    totaalPrijsLabel.setPrefSize(150.0, 68.0);
                    totaalPrijsLabel.setMinWidth(150);
                    totaalPrijsLabel.setMinWidth(150);

                    productBox.getChildren().addAll(productNameLabel, stukprijsLabel, btwPrijsLabel, aantalLabel, totaalPrijsLabel);
                    productenList.getChildren().add(productBox);
                }
                
                productenList.setSpacing(10.0);
                productenList.setAlignment(Pos.TOP_LEFT);

                highlightSelectedBox(bestellingBox);
            });

            bestellingBox.getChildren().addAll(orderIdLabel, datumLabel, bedragLabel, orderStatusLabel, betalingStatusLabel);
            bestellingList.getChildren().add(bestellingBox);
        }
    }


    private void highlightSelectedBox(HBox selectedBox) {
        for (Node node : bestellingList.getChildren()) {
            if (node instanceof HBox) {
                HBox box = (HBox) node;
                box.setStyle("-fx-border-color: #D3D3D3;");
            }
        }
        selectedBox.setStyle("-fx-border-color: blue;");
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bestellingen.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            if (primaryStage != null) {
                primaryStage.setScene(scene);
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


}

