package game;

/**
 * An enumeration containing all the possible movements.
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

        NORTH.code = 'N';
        SOUTH.code = 'S';
        EAST.code = 'E';
        WEST.code = 'W';

        NORTH.vector = new Vector(0, -1);
        SOUTH.vector = new Vector(0, 1);
        EAST.vector = new Vector(1, 0);
        WEST.vector = new Vector(-1, 0);
    }

    private Direction opposite;

    private char code;

    private Vector vector;

    /**
     * @return the opposite {@link Direction}
     */
    public Direction getOppositeDirection() {
        return opposite;
    }

    /**
     * @return the code of {@link Direction}
     */
    public char getCode() {
        return code;
    }

    public Vector getVector() {
        return vector;
    }

    public static Direction fromCode(char c) {
        if (c == NORTH.getCode())
            return NORTH;
        else if (c == SOUTH.getCode())
            return SOUTH;
        else if (c == EAST.getCode())
            return EAST;
        else if (c == WEST.getCode())
            return WEST;
        throw new RuntimeException("Unknown direction code");
    }


}
