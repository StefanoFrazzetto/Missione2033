package gameobjects;

/**
 * An enumeration containing all the possible movements in the {@link GameGrid}.
 * <p>
 * Created by Vittorio on 05-Oct-16.
 */
public enum Direction {
    /**
     * (0, -1)
     */
    NORTH,
    /**
     * (1, 0)
     */
    EAST,
    /**
     * (0, 1)
     */
    SOUTH,
    /**
     * (-1, 0)
     */
    WEST;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }

    private Direction opposite;

    /**
     * @return the opposite {@link Direction}
     */
    public Direction getOppositeDirection() {
        return opposite;
    }
}
