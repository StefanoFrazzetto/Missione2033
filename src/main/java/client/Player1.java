package client;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;

public class Player1 {

    public GridPane gameGrid;

    public void initialize() {
        System.out.println("Controller is initialising Player1");

        Platform.runLater(() -> {

            gameGrid.getScene().setOnKeyPressed(event -> {

            });


        });
    }
}