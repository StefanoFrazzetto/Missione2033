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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class PlayerController {

    public static final Color ENEMY_COLOR = Color.PURPLE;
    public static final Color WALL_COLOR = Color.BLACK;
    public static final Color FLOOR_COLOR = Color.WHITE;
    public static final Color OPEN_DOOR_COLOR = Color.GREEN;
    public static final Color CLOSED_DOOR_COLOR = Color.RED;
    public static final Color EXIT_COLOR = Color.ORANGE;

    public GridPane gridPane;

    protected PlayerModel model;

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
                node = stack;

            } else if (entity instanceof Enemy) {
                rect.setFill(ENEMY_COLOR);
            } else if (entity instanceof Agent) {
                ImagePattern imagePattern = new ImagePattern(image);

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
