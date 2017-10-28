package gameobjects;

import java.io.InputStream;
import java.io.Serializable;
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
     * <p>
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

        for (Entity entity : entityList) {
            if (entity instanceof Agent) {
                agent = (Agent) entity;
                break;
            }
        }

        if (agent == null)
            throw new RuntimeException("No agent found!");
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
     * <p>
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
        int x = agent.getxCoordinate();
        int y = agent.getyCoordinate();

        switch (direction) {
            case NORTH:
                x -= 1;
                break;
            case EAST:
                y += 1;
                break;
            case SOUTH:
                x += 1;
                break;
            case WEST:
                y -= 1;
                break;
        }

        if (gameGrid.getGameObjectAt(x, y) != GameObject.FLOOR) {
            return;
        }

        for (Entity collidable : entityList) {
            if (collidable.getxCoordinate() == x && collidable.getyCoordinate() == y)
                if (collidable instanceof Door) {
                    Door door = (Door) collidable;

                    if (!door.isOpen())
                        return;

                } else return;
        }

        agent.setCoordinates(x, y);
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

    public void openDoors(GameObject doorType) {
        for (Entity entity : entityList) {
            if (entity instanceof Door && ((Door) entity).getType() == doorType) {
                ((Door) entity).open();
            }
        }
    }

    public void closeDoors(GameObject doorType) {
        for (Entity entity : entityList) {
            if (entity instanceof Door && ((Door) entity).getType() == doorType) {
                ((Door) entity).close();
            }
        }
    }
}
