package gameobjects;

/**
 * Entity
 *
 * @author stefano
 * @version 1.0.0
 */
abstract class Entity {

    private int health;

    Entity(int health) {
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
