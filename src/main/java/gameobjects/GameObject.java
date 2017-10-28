package gameobjects;

import java.io.Serializable;
import java.lang.Character;

/**
 * GameObject represents the objects in a game.
 * <p>
 * Each object has a symbol which is used to decode the save files.
 */
public enum GameObject implements Serializable {
    WALL('W'),
    FLOOR(' '),
    PLAYER('P'),
    DOOR('D'),
    ENEMY('E');

    private final char symbol;

    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * Return the enum associated with a char.
     *
     * If the object is not recognized, returns a wall.
     *
     * @param c - the char to look for
     * @return the {@link GameObject} corresponding to the char
     */
    public static GameObject fromChar(char c) {
        for (GameObject t : GameObject.values()) {
            if (Character.toUpperCase(c) == t.symbol) {
                return t;
            }
        }

        return WALL;
    }

    /**
     * Return the string representation of the symbol.
     *
     * @return String
     */
    public String getStringSymbol() {
        return String.valueOf(symbol);
    }

    /**
     * Returns the symbol associated with the game object.
     *
     * @return the symbol associated with the game object.
     */
    public char getCharSymbol() {
        return symbol;
    }
}