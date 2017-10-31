package game.entities;

import game.weapons.Shotgun;
import game.weapons.Weapon;

/**
 * Agent
 *
 * @author stefano
 * @version 1.0.0
 */
public class Agent extends Character {

    /** The default health value */
    private final static int DEFAULT_PLAYER_HEALTH = 100;

    private final static int DEFAULT_PLAYER_STRENGTH = 10;


    /**
     * Create a new player.
     */
    public Agent() {
        super(DEFAULT_PLAYER_HEALTH, DEFAULT_PLAYER_STRENGTH);
        Weapon weapon = new Shotgun();
        this.equipWeapon(weapon);
    }
}
