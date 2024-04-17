package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private DomeinController controller;

	public SceneController(DomeinController controller){
		this.controller = controller;
	}
	
	public void SwitchToMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("bestellingen.fxml"));
			SceneController scr = new SceneController(controller);
			loader.setController(scr);
			Parent root = loader.load();
//			hsc.vertaal();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
