package client;

import game.entities.Agent;
import game.entities.Enemy;
import game.entities.Entity;
import game.gridobjects.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class PlayerController {

    private static final Image agent = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/boy.40x40.png"));
    private static final Image enemy = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/Enemy40x40.png"));
    private static final Image wall = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/maps/walls/Brick.png"));
    private static final Image floor = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/maps/floors/hardwood1.png"));
    //private static final Image door = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/maps/floors/stairs.png"));
    private static final Image exit = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/maps/blueprint/decalExit.png"));
    private static final Image grass = new Image(PlayerController.class.getClassLoader().getResourceAsStream("Textures/maps/floors/grasslong.png"));

    private static final Paint OPEN_DOOR_COLOR = Color.TRANSPARENT;
    private static final Paint CLOSED_DOOR_COLOR = Color.RED;

    private static final Paint FLOOR_COLOR = new ImagePattern(floor);//Color.WHITE;
    private static final Paint GRASS_COLOR = new ImagePattern(grass);//Color.WHITE;
    private static final Paint WALL_COLOR = new ImagePattern(wall);
    private static final Paint EXIT_COLOR = new ImagePattern(exit);//Color.WHITE;

    private static final Paint ENEMY_COLOR = new ImagePattern(enemy);
    private static final Paint AGENT_COLOR = new ImagePattern(agent);

    public GridPane gridPane;

    protected PlayerModel model;

    public void initialize() {
        System.out.printf("%s is initialising...%n", getClass().getName());

        Platform.runLater(() -> {
            gridPane.getScene().setOnKeyPressed(getKeyEventEventHandler());

            draw();
        });
    }

    protected abstract EventHandler<KeyEvent> getKeyEventEventHandler();

    protected abstract boolean showCharacters();

    public void draw() {
        draw(0, 0, model.level.getGameGrid().getWidth(), model.level.getGameGrid().getWidth());
    }

    private Node render(Paint fill) {
        return new Rectangle(getRectSize(), getRectSize(), fill);
    }

    private Node renderDoor(Door door, int x, int y) {
        Rectangle rect = new Rectangle(getRectSize(), getRectSize());

        if (door.isOpen()) {
            rect.setFill(OPEN_DOOR_COLOR);
        } else {
            rect.setFill(CLOSED_DOOR_COLOR);
        }


        Text text = new Text(String.valueOf(door.getDoorType()));
        StackPane stack = new StackPane();
        stack.getChildren().add(rect);
        stack.getChildren().add(text);

        // Attempts to guess door orientation
        try {
            GridObject objectToTheRight = model.getGameGrid().get(x + 1, y);

            if (objectToTheRight instanceof Wall || objectToTheRight instanceof Door)
                rect.setHeight(getRectSize() / 2);
            else
                rect.setWidth(getRectSize() / 2);
        } finally {
            // NON  FACEMO UNA MINGHIA
        }

        return stack;
    }

    private boolean isTileVisible(int x, int y, int minx, int miny, int maxwidth, int maxheight) {
//        if (x < minx)
//            return false;
//        if (x > minx + maxwidth)
//            return false;
//        if (y < miny)
//            return false;
//        return y <= miny + maxheight;

        return true;
    }

    public void draw(int minx, int miny, int maxwidth, int maxheight) {
        gridPane.getChildren().clear();

        model.level.getFloorGrid().forEach((tile, x, y) -> {
            if (!isTileVisible(x, y, minx, miny, maxwidth, maxheight)) {
                return;
            }

            StackPane stackPane = new StackPane();


            if (tile instanceof Grass) {
                stackPane.getChildren().add(render(GRASS_COLOR));
            } else {
                stackPane.getChildren().add(render(FLOOR_COLOR));
            }


            GridObject gridObject = model.level.getGameGrid().get(x, y);

            if (gridObject instanceof Wall) {
                stackPane.getChildren().add(render(WALL_COLOR));
            } else if (gridObject instanceof Door) {
                stackPane.getChildren().add(renderDoor((Door) gridObject, x, y));
            } else if (gridObject instanceof Exit) {
                stackPane.getChildren().add(render(EXIT_COLOR));
            }

            gridPane.add(stackPane, x, y);
        });

        if (showCharacters())
            for (Entity entity : model.level.getEntities()) {
                Rectangle rect = new Rectangle(getRectSize(), getRectSize());

                int x = (int) entity.getX();
                int y = (int) entity.getY();

                if (!isTileVisible(x, y, minx, miny, maxwidth, maxheight)) {
                    continue;
                }

                if (entity instanceof Agent) {
                    rect.setFill(AGENT_COLOR);
                } else if (entity instanceof Enemy) {
                    rect.setFill(ENEMY_COLOR);
                }

                gridPane.add(rect, x, y);
            }

        gridPane.getScene().getWindow().sizeToScene();
    }

    protected int getRectSize() {
        return 40;
    }
}
