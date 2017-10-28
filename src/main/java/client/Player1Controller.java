package client;

import gameobjects.GameGrid;
import gameobjects.GameObject;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player1Controller {

    public GridPane gridPane;
    private GameGrid gameGrid;

    public Player1Controller() {
        this.gameGrid = Player1App.getGameGrid();
    }

    public void initialize() {
        System.out.println("Controller is initialising Player1Controller");

        Platform.runLater(() -> {

            gridPane.getScene().setOnKeyPressed(event -> {

            });

            draw();


        });
    }

    public void draw() {
        GameGrid.GridIterator iterator = (GameGrid.GridIterator) gameGrid.iterator();

        while (iterator.hasNext()) {
            GameObject next = iterator.next();
            Rectangle rect = new Rectangle(10, 10);

            int x = iterator.getColumn();
            int y = iterator.getRow();


            if (next == null) {
                rect.setFill(Color.WHITE);
            } else {
                switch (next) {
                    case WALL:
                        rect.setFill(Color.BLACK);
                        break;
                    case FLOOR:
                        rect.setFill(Color.WHITE);
                        break;
                    case PLAYER:
                        rect.setFill(Color.GREEN);
                        break;
                }
            }


            gridPane.add(rect, x, y);
        }

        gridPane.getScene().getWindow().sizeToScene();
    }
}