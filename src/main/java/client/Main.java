package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("main.fxml");

        assert resource != null;

        Parent root = FXMLLoader.load(resource);

        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GUTS 2017");

        primaryStage.show();

        primaryStage.sizeToScene();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                StartAsPlayerOne();
            }
        });

        Main.primaryStage = primaryStage;
    }

    public void StartAsPlayerOne() {
        Player1App app = new Player1App();

        app.start(primaryStage);
    }

    public void StartAsPlayerTwo() {

    }

    public void closeGame() {
        System.exit(0);
    }

}
