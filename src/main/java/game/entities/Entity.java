package game.entities;

import java.io.Serializable;

import static java.lang.Math.round;

/**
 * Entity
 *
 * @author stefano
 * @version 1.0.0
 */
public class Entity implements Serializable {
    private double row;

    private double column;

    public void setCoordinates(double x, double y) {
        this.row = x;
        this.column = y;
    }

    public double getX() {
        return row;
    }

    public double getY() {
        return column;
    }

    public int getDistance(Entity entity) {
        return (int) round(Math.sqrt(Math.pow(entity.getX() - this.getX(), 2) +
                Math.pow(entity.getY() - this.getY(), 2)));
    }
}
