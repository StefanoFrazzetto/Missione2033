package gameobjects;

/**
 * Agent
 *
 * @author stefano
 * @version 1.0.0
 */
public class Agent extends Character {

    /** The default health value */
    private final static int DEFAULT_PLAYER_HEALTH = 100;


    /**
     * Create a new player.
     */
    public Agent() {
        super(DEFAULT_PLAYER_HEALTH);
    }
}
