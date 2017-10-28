package gameobjects;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * GameEngine
 *
 * @author stefano
 * @version 1.0.0
 */
public class GameEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The agent
     */
    transient private Agent agent;

    /**
     * The current level
     */
    transient private Level currentLevel;

    /**
     * The game status
     *
     * TODO: migrate to enum
     */
    transient private String status = "RUNNING";

    /**
     * The game grid containing the object
     */
    private GameGrid gameGrid;

    /**
     * The list of entities in the level
     */
    private List<Entity> entityList;

    public GameEngine() {
        initializeGame();
    }

    public void initializeGame() {
        String filename = "level1.txt";
        loadLevel(filename);
    }

    /**
     * Load the level from a txt file.
     *
     * @param fileName the file name
     */
    public void loadLevel(String fileName) {
        Objects.requireNonNull(fileName);

        // Import the level from a file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        currentLevel = new Level(inputStream);
        gameGrid = currentLevel.getGameGrid();
        entityList = currentLevel.getEntityList();
    }

    /**
     * @return the game grid
     */
    public GameGrid getGameGrid() {
        return gameGrid;
    }

    /**
     * @return the list containing the entities
     */
    public List<Entity> getEntityList() {
        return entityList;
    }

    /**
     * Initialise the right player type by decoding the string.
     *
     * We should probably check if the player type was already initialised
     * (this would avoid other players from intruding the game).
     *
     * @param playerTypeString the player type string
     * @throws IllegalArgumentException if string does not match any player type
     */
    public PlayerType handlePlayerInit(String playerTypeString) throws IllegalArgumentException {
        PlayerType playerType = PlayerType.fromString(playerTypeString);

        if (playerType == PlayerType.AGENT) {
            initAgent();
        }

        return playerType;
    }

    public void handleMovement(Direction direction) {
        // DO stuff
    }

    /**
     * Initialize the agent
     */
    private void initAgent() {
        this.agent = new Agent();
    }

    public String getStatus() {
        return status;
    }

}
