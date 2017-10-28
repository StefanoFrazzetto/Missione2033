package gameobjects;

/**
 * Player
 *
 * @author stefano
 * @version 1.0.0
 */
public class Player extends Entity {

    /** The default health value */
    private final static int DEFAULT_PLAYER_HEALTH = 100;

    /** The player type */
    private PlayerType playerType;

    /**
     * Create a new player.
     *
     * @param playerType the player type
     */
    public Player(PlayerType playerType) {
        super(DEFAULT_PLAYER_HEALTH);
        this.playerType = playerType;
    }

    /**
     * @return the playerType
     */
    public PlayerType getPlayerType() {
        return playerType;
    }
}
