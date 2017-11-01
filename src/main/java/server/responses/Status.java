package server.responses;

import game.GameEngine;
import utils.Serializer;

import java.io.IOException;

/**
 * Status
 *
 * @author stefano
 * @version 1.0.0
 */
public class Status {
    private final String status;
    private final String serializedLevel;

    public static Status fromGameEngine(GameEngine gameEngine) {
        String status = "Unknown status";
        String serializedLevel = null;

        try {
            status = gameEngine.getGameStatus().getString();
            serializedLevel = Serializer.toString(gameEngine.getLevel());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Status(status, serializedLevel);
    }

    public Status(String status, String serializedLevel) {
        this.status = status;
        this.serializedLevel = serializedLevel;
    }

    public String getSerializedLevel() {
        return serializedLevel;
    }

    public String getStatus() {
        return status;
    }


}
