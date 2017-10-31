package game.weapons;

import game.entities.Character;

import java.io.Serializable;

/**
 * Weapon
 *
 * @author stefano
 * @version 1.0.0
 */
abstract public class Weapon implements Serializable {

    private String name;

    private int attackPower;

    private int range;

    public Weapon(String name, int attackPower, int range) {
        this.name = name;
        this.attackPower = attackPower;
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getName() {
        return name;
    }

    /**
     * Calculate the damage that an entity will inflict.
     *
     * @param attacker the attacker
     * @return the damage inflicted
     */
    public int calculateDamage(Character attacker) {
        return this.getAttackPower() * attacker.getStrength();
    }
}
