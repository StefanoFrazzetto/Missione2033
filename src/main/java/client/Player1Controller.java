package client;

import com.mashape.unirest.http.exceptions.UnirestException;
import gameobjects.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Player1Controller {

    public GridPane gridPane;
    private Player1Model model;

    private Image image = new Image(getClass().getClassLoader().getResourceAsStream("boy.png"));

    public Player1Controller() throws UnirestException {
        model = new Player1Model();
        model.initGame();
    }

    public void initialize() {
        System.out.printf("%s is initialising...%n", getClass().getName());

        Platform.runLater(() -> {
            gridPane.getScene().setOnKeyPressed(event -> {
                Direction d = null;

                switch (event.getText().toUpperCase()) {
                    case "W":
                        d = Direction.NORTH;
                        break;
                    case "S":
                        d = Direction.SOUTH;
                        break;
                    case "D":
                        d = Direction.EAST;
                        break;
                    case "A":
                        d = Direction.WEST;
                        break;
                    default:
                        System.out.printf("[%s]%n", event.getCode());
                }

                if (d != null)
                    model.movePlayer(d, () -> {
                        try {
                            System.err.println("ORCO");
                            model.updateEntityList();
                        } catch (UnirestException | ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        } finally {
                            draw();
                        }
                    });
            });

            draw();
        });
    }

    public void draw() {
        int size = 25;

        GameGrid.GridIterator iterator = (GameGrid.GridIterator) model.getGameGrid().iterator();

        while (iterator.hasNext()) {
            GameObject next = iterator.next();
            Rectangle rect = new Rectangle(size, size);

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

        for (Entity asd : model.entityList) {
            Rectangle rect = new Rectangle(size, size);

            rect.setFill(Color.RED);

            if (asd instanceof Agent) {
                ImagePattern imagePattern = new ImagePattern(image);

                //image.is

                WritableImage wImage = new WritableImage(size, size);


                rect.setFill(imagePattern);
            }

            gridPane.add(rect, asd.getyCoordinate(), asd.getxCoordinate());
        }

        gridPane.getScene().getWindow().sizeToScene();
    }
}