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



    Character(int health) {
        this.health = health;
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
