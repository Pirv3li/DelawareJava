package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BestellingController {

    private SceneController switch2;
    private DomeinController controller;
    private Parent root;
    private Stage primaryStage;
    
    @FXML
    private Button Login;
    @FXML
    private TextField gebruikersnaam;
    @FXML
    private PasswordField wachtwoord;
    @FXML
    private Hyperlink wachtwoordVergeten;
    @FXML
    private Text loginError;

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

