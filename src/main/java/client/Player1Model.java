package client;

import gameobjects.Direction;
import javafx.geometry.Point2D;

import java.util.List;

public class Player1Model extends PlayerModel {
    public void movePlayer(Direction direction) {
        System.out.println(direction);
    }

    public Point2D getPlayerPosition() {
        return new Point2D(0, 0);
    }

    public List<Point2D> getEnemiesPositions() {
        return null;
    }
}
