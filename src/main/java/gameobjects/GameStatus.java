package gameobjects;


import java.io.Serializable;

/**
 * GameStatus
 *
 * @author stefano
 * @version 1.0.0
 */
public enum GameStatus implements Serializable {
    RUNNING("RUNNING"),
    VICTORY("VICTORY"),
    GAME_OVER("GAME_OVER");

    private final String status;

    GameStatus(final String status) {
        this.status = status;
    }

    public static GameStatus fromString(String string) {
        for (GameStatus gs : GameStatus.values()) {
            if (gs.getString().equals(string.toUpperCase())) {
                return gs;
            }
        }

        throw new IllegalArgumentException("The game status does not exist");
    }

    /**
     * Return the string representing the status.
     *
     * @return String
     */
    public String getString() {
        return status ;
    }
}
