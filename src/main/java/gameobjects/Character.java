package gameobjects;

import java.awt.*;
import java.io.Serializable;

/**
 * Character
 *
 * @author stefano
 * @version 1.0.0
 */
abstract class Character extends Entity implements Serializable {

    private int health;

    private int xCoordinate;

    private int yCoordinate;

    Character(int health) {
        this.health = health;
    }

    public void setCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Point getCoordinates() {
        return new Point(xCoordinate, yCoordinate);
    }

    /**
     * @return the entity health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Decrease the entity health by an amount;
     *
     * @param amount the amount of health to remove
     * @return the updated health
     */
    public int decreaseHealth(int amount) {
        health -= amount;

        return getHealth();
    }
}