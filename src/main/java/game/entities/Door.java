package game.entities;

import game.GameObject;

/**
 * Door
 *
 * @author stefano
 * @version 1.0.0
 */
public class Door extends Entity {

    private boolean open;

    private GameObject type;

    public Door(GameObject doorType, boolean open) {
        this.type = doorType;
        this.open = open;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public void toggleDoor() {
        this.open = !this.open;
    }

    public GameObject getType() {
        return type;
    }

    public boolean isOpen() {
        return open;
    }
}
