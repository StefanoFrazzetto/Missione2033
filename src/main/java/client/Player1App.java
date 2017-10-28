package client;

import gameobjects.GameGrid;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Player1App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    private static GameGrid gameGrid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform.runLater(() -> {
            // HERE WE SHOULD LOAD THE MAP

            initInterface(primaryStage);
        });
    }

    private void initInterface(Stage primaryStage) {
        URL resource = getClass().getClassLoader().getResource("player1.fxml");

        assert resource != null;

        Parent root = null;

        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;

        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("GUTS 2017");

        Player1App.primaryStage = primaryStage;

        primaryStage.show();
    }
}
