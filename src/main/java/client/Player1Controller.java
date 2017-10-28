package client;

import com.mashape.unirest.http.exceptions.UnirestException;
import gameobjects.Direction;
import gameobjects.GameGrid;
import gameobjects.GameObject;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player1Controller {

    public GridPane gridPane;
    private Player1Model model;

    public Player1Controller() throws UnirestException {
        model = new Player1Model();
        model.initGame();
    }

    public void initialize() {
        System.out.printf("%s is initialising...%n", getClass().getName());

        Platform.runLater(() -> {
            gridPane.getScene().setOnKeyPressed(event -> {
                switch (event.getText().toUpperCase()) {
                    case "W":
                        model.movePlayer(Direction.NORTH);
                        break;
                    case "S":
                        model.movePlayer(Direction.SOUTH);
                        break;
                    case "D":
                        model.movePlayer(Direction.EAST);
                        break;
                    case "A":
                        model.movePlayer(Direction.WEST);
                        break;
                    default:
                        System.out.printf("[%s]%n", event.getCode());
                }
            });

            draw();
        });
    }

    public void draw() {
        GameGrid.GridIterator iterator = (GameGrid.GridIterator) model.getGameGrid().iterator();

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


            gridPane.add(rect, y, x);
        }

        gridPane.getScene().getWindow().sizeToScene();
    }
}