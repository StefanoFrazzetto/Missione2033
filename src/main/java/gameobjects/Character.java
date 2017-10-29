package gameobjects;

import gameobjects.weapons.Weapon;

import static java.lang.Math.round;

/**
 * Character
 *
 * @author stefano
 * @version 1.0.0
 */
public abstract class Character extends Entity {

    private int health;

    private int strength;

    private Weapon weapon;

    Character(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    /**
     * Equip a weapon.
     *
     * @param weapon the weapon to equip
     */
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public int getDistance(Character character) {
        return (int) round(Math.sqrt(Math.pow(character.getRow() - this.getRow(), 2) +
                Math.pow(character.getColumn() - this.getColumn(), 2)));
    }

    public int getStrength() {
        return strength;
    }

    public void attack(Character character) {
        if (getDistance(character) <= weapon.getRange()) {
            character.decreaseHealth(weapon.calculateDamage(this));
        }
    }
}
