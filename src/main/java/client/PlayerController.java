package client;

import gameobjects.Agent;
import gameobjects.Entity;
import gameobjects.GameGrid;
import gameobjects.GameObject;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class PlayerController {
    public GridPane gridPane;

    PlayerModel model;

    private Image image = new Image(getClass().getClassLoader().getResourceAsStream("boy.png"));

    public void initialize() {
        System.out.printf("%s is initialising...%n", getClass().getName());

        Platform.runLater(() -> {
            gridPane.getScene().setOnKeyPressed(getKeyEventEventHandler());

            draw();
        });
    }

    protected abstract EventHandler<KeyEvent> getKeyEventEventHandler();

    protected abstract boolean showCharacters();

    void draw() {
        GameGrid.GridIterator iterator = (GameGrid.GridIterator) model.getGameGrid().iterator();


        while (iterator.hasNext()) {
            GameObject next = iterator.next();
            Rectangle rect = new Rectangle(getRectSize(), getRectSize());

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

        if (showCharacters())
            for (Entity asd : model.entityList) {
                Rectangle rect = new Rectangle(getRectSize(), getRectSize());

                rect.setFill(Color.RED);

                if (asd instanceof Agent) {
                    ImagePattern imagePattern = new ImagePattern(image);

                    //image.is

                    WritableImage wImage = new WritableImage(getRectSize(), getRectSize());


                    rect.setFill(imagePattern);
                }

                gridPane.add(rect, asd.getyCoordinate(), asd.getxCoordinate());
            }

        gridPane.getScene().getWindow().sizeToScene();
    }

    protected int getRectSize() {
        return 25;
    }
}
