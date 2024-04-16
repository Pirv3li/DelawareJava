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
import javafx.stage.Stage;

public class AanmeldenController {

		private SceneController switch2;
		private DomeinController controller;
		private Parent root;
		
		@FXML
		private Button Login;
		private TextField gebruikersnaam;
		private PasswordField wachtwoord;
		private Hyperlink wachtwoordVergeten;

		public AanmeldenController(DomeinController controller, SceneController switch2) {
			this.controller = controller;
			this.switch2 = switch2;
		}

		public void start(Stage stage) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
				loader.setController(this);
				root = loader.load();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	    public Parent getRoot() {
	        return root; // Return the stored root node
	    }

//		public void SwitchToTaalScherm(ActionEvent event) {
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("taalscherm.fxml"));
//				loader.setController(new TaalController(controller));
//				Parent root = loader.load();
//				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//				Scene scene = new Scene(root);
//				stage.setScene(scene);
//				stage.show();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//	}
	
}
