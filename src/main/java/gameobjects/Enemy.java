package gameobjects;

/**
 * Enemy
 *
 * @author stefano
 * @version 1.0.0
 */
public class Enemy extends Character {

    /** The default health value */
    private static final int DEFAULT_ENEMY_HEALTH = 100;

    /**
     * Create a new enemy.
     */
    public Enemy() {
        super(DEFAULT_ENEMY_HEALTH);
    }
}
