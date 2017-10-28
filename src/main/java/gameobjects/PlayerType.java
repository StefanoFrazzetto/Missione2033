package gameobjects;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * PlayerType
 *
 * @author stefano
 * @version 1.0.0
 */
public enum PlayerType {
    AGENT("agent"),
    OPERATOR("operator");

    private final String type;

    PlayerType(final String type) {
        this.type = type;
    }

    /**
     * Return a PlayerType if the string matches one of the enums.
     *
     * @param string the player type string
     * @return the player type associated with the string
     * @throws IllegalArgumentException if the string does not match any enum
     */
    public static PlayerType fromString(String string) throws InvalidArgumentException {
        for (PlayerType playerType : PlayerType.values()) {
            if (string.toLowerCase().equals(playerType.getType())) {
                return playerType;
            }
        }

        throw new IllegalArgumentException("Invalid player type supplied");
    }

    public String getType() {
        return type;
    }
}
