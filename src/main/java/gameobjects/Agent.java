package gameobjects;

/**
 * Agent
 *
 * @author stefano
 * @version 1.0.0
 */
public class Agent extends Entity {

    /** The default health value */
    private final static int DEFAULT_PLAYER_HEALTH = 100;

//    /** The player type */
//    private PlayerType playerType;

    /**
     * Create a new player.
     */
    public Agent() {
        super(DEFAULT_PLAYER_HEALTH);
//        this.playerType = playerType;
    }

//    /**
//     * @return the playerType
//     */
//    public PlayerType getPlayerType() {
//        return playerType;
//    }
}
