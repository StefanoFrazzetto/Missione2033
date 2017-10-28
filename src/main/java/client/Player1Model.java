package client;

import com.mashape.unirest.http.Unirest;
import gameobjects.Direction;
import javafx.geometry.Point2D;

import java.util.List;

public class Player1Model extends PlayerModel {
    public void movePlayer(Direction direction) {
        Unirest.get(String.format("%s/play/move?d=%s", Main.getHost(), direction.getCode())).asBinaryAsync();
    }

    public Point2D getPlayerPosition() {
        return new Point2D(0, 0);
    }

    public List<Point2D> getEnemiesPositions() {
        return null;
    }
}
