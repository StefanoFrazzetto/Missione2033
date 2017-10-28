package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class PlayerApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform.runLater(() -> {
            initInterface(primaryStage);
        });
    }

    private void initInterface(Stage primaryStage) {
        URL resource = getClass().getClassLoader().getResource(getFxml());

        assert resource != null;

        Parent root = null;

        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("GUTS 2017");

        primaryStage.show();
    }

    public abstract String getFxml();
}
