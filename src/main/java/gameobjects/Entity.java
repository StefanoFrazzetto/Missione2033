package gameobjects;

import java.awt.*;
import java.io.Serializable;

import static java.lang.Math.round;

/**
 * Entity
 *
 * @author stefano
 * @version 1.0.0
 */
public class Entity implements Serializable {
    private int row;

    private int column;

    public void setCoordinates(int x, int y) {
        this.row = x;
        this.column = y;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getDistance(Entity entity) {
        return (int) round(Math.sqrt(Math.pow(entity.getRow() - this.getRow(), 2) +
                Math.pow(entity.getColumn() - this.getColumn(), 2)));
    }

    public Point getCoordinates() {
        return new Point(row, column);
    }
}
