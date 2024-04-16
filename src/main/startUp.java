package main;

import domein.DomeinController;
import gui.AanmeldenController;
import gui.SceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class startUp extends Application {
    private DomeinController dc;
    private SceneController sc;

    @Override
    public void start(Stage stage) throws Exception {
        // Instantiate DomeinController and SceneController
        dc = new DomeinController();
        sc = new SceneController(dc);

        // Instantiate AanmeldenController with dependencies
        AanmeldenController aanmeldenController = new AanmeldenController(dc, sc);
        aanmeldenController.start(stage); // Start the AanmeldenController

        // Create the scene with the root node from AanmeldenController
        Scene scene = new Scene(aanmeldenController.getRoot());

        // Set the stage properties and show
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
