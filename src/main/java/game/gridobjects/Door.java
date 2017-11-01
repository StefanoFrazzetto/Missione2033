package game.gridobjects;

/**
 * Door
 *
 * @author stefano
 * @version 1.0.0
 */
public class Door extends GridObject {

    private boolean closed;

    private char doorType;

    public Door(char doorType) {
        this(doorType, true);
    }

    public Door(char doorType, boolean closed) {
        this.doorType = doorType;
        this.closed = closed;
    }

    public void open() {
        closed = false;
    }

    public void close() {
        closed = true;
    }

    public void toggleDoor() {
        this.closed = !this.closed;
    }

    public char getDoorType() {
        return doorType;
    }

    public boolean isOpen() {
        return !closed;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isBlocking() {
        return closed;
    }
}
