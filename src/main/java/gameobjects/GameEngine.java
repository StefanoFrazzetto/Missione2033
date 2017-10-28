package gameobjects;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * GameEngine
 *
 * @author stefano
 * @version 1.0.0
 */
public class GameEngine {

    private Player agent;
    private Player operator;

    private GameGrid objectsGrid;
    private GameGrid entitiesGrid;


    public GameEngine() {
        initializeGame();
    }

    private void initializeGame() {
        loadLevel();
    }

    private void loadLevel() {
        // Import the level from a file
    }

    /**
     * Initialise the right player type by decoding the string.
     *
     * We should probably check if the player type was already initialised
     * (this would avoid other players from intruding the game).
     *
     * @param playerTypeString the player type string
     * @throws InvalidArgumentException if string does not match any player type
     */
    public PlayerType handlePlayerInit(String playerTypeString) throws InvalidArgumentException {
        PlayerType playerType = PlayerType.fromString(playerTypeString);

        if (playerType == PlayerType.OPERATOR) {
            initOperator();
        } else {
            initAgent();
        }

        return playerType;
    }

    private void initAgent() {
        this.agent = new Player(PlayerType.AGENT);
    }

    private void initOperator() {
        this.operator = new Player(PlayerType.OPERATOR);
    }

}
