package gameobjects;

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

}
