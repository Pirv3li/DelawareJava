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

public class AanmeldenController {

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

    public AanmeldenController(DomeinController controller, Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = gebruikersnaam.getText();
        String password = wachtwoord.getText();
        boolean loggedIn = controller.Aanmelden(username, password);
        if (loggedIn) {
            // Navigate to the next scene or perform any other action
            System.out.println("Login successful!");
        } else {
            // Display login error message
            loginError.setText("Invalid username or password.");
        }
    }
}

