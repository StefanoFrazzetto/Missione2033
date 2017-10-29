package client;

import gameobjects.*;
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
        draw(0, 0, model.gameGrid.COLUMNS, model.gameGrid.ROWS);
    }

    public void draw(int minx, int miny, int maxwidth, int maxheight) {
        gridPane.getChildren().clear();

        GameGrid.GridIterator iterator = (GameGrid.GridIterator) model.getGameGrid().iterator();

        while (iterator.hasNext()) {

            GameObject next = iterator.next();
            Rectangle rect = new Rectangle(getRectSize(), getRectSize());

            int x = iterator.getColumn();
            int y = iterator.getRow();

            if (x < minx)
                continue;
            if (x > minx + maxwidth)
                continue;
            if (y < miny)
                continue;
            if (y > miny + maxheight)
                continue;

            if (next == null) {
                rect.setFill(FLOOR_COLOR);
            } else {
                switch (next) {
                    case WALL:
                        rect.setFill(WALL_COLOR);
                        break;
                    case FLOOR:
                        rect.setFill(FLOOR_COLOR);
                        break;
                    case GRASS:
                        rect.setFill(GRASS_COLOR);
                        break;
                }
            }


            gridPane.add(rect, y, x);
        }

        for (Entity entity : model.entityList) {
            Node node = null;
            Rectangle rect = new Rectangle(getRectSize(), getRectSize());
            rect.setFill(Color.RED);

            int x = entity.getRow();
            int y = entity.getColumn();

            if (x < minx)
                continue;
            if (x > minx + maxwidth)
                continue;
            if (y < miny)
                continue;
            if (y > miny + maxheight)
                continue;

            if (entity instanceof Door) {
                if (((Door) entity).isOpen()) {
                    rect.setFill(OPEN_DOOR_COLOR);
                } else {
                    rect.setFill(CLOSED_DOOR_COLOR);
                }

                Text text = new Text(((Door) entity).getType().getStringSymbol());
                StackPane stack = new StackPane();
                stack.getChildren().add(rect);
                stack.getChildren().add(text);

                try {
                    if (model.gameGrid.getGameObjectAt(x + 1, y) != GameObject.FLOOR)
                        rect.setWidth(getRectSize() / 2);
                    else
                        rect.setHeight(getRectSize() / 2);
                } finally {
                    // NON  FACEMO UNA MINGHIA
                }

                node = stack;

            } else if (entity instanceof Enemy) {
                rect.setFill(ENEMY_COLOR);
            } else if (entity instanceof Agent) {
                ImagePattern imagePattern = new ImagePattern(agent);

                rect.setFill(imagePattern);
            } else if (entity instanceof Exit) {
                rect.setFill(EXIT_COLOR);
            }

            if (node == null) {
                if (!showCharacters())
                    continue;

                node = rect;
            }

            gridPane.add(node, entity.getColumn(), entity.getRow());
        }

        gridPane.getScene().getWindow().sizeToScene();
    }

    protected int getRectSize() {
        return 40;
    }
}
