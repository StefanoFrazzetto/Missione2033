package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Player1App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("player1.fxml");

        assert resource != null;

        Parent root = FXMLLoader.load(resource);

        assert root != null;

        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("GUTS 2017");

        Player1App.primaryStage = primaryStage;

        primaryStage.show();
    }
}
