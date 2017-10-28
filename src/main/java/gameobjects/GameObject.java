package gameobjects;

/**
 * GameObject represents the objects in a game.
 * <p>
 * Each object has a symbol which is used to decode the save files.
 */
public enum GameObject {
    WALL('W'),
    FLOOR(' '),
    PLAYER('P');

    private final char symbol;

    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * Return the enum associated with a char.
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

        return null;
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