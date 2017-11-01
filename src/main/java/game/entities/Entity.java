package game.entities;

import java.io.Serializable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Entity
 *
 * @author stefano
 * @version 1.0.0
 */
public class Entity implements Serializable {
    private double x;

    private double y;

    public Entity() {
        this(0, 0);
    }

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistance(Entity entity) {
        return sqrt(getSquaredDistance(entity));
    }

    public double getSquaredDistance(Entity entity) {
        return pow(entity.getX() - this.getX(), 2) + pow(entity.getY() - this.getY(), 2);
    }
}
