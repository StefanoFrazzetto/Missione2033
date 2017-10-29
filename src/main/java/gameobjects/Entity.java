package gameobjects;

import java.awt.*;
import java.io.Serializable;

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

    public Point getCoordinates() {
        return new Point(row, column);
    }
}
