package server.responses;

import gameobjects.GameEngine;
import utils.Serializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * Status
 *
 * @author stefano
 * @version 1.0.0
 */
public class Status {
    private final String status;
    private final String serializedGameGrid;
    private final String serializedEntityList;

    public static Status fromGameEngine(GameEngine gameEngine) {
        String status = "Unknown status";
        String gameGrid = null;
        String entityList = null;

        try {
            gameGrid = Serializer.toString(gameEngine.getGameGrid());
            entityList = Serializer.toString((Serializable) gameEngine.getEntityList());
            status = gameEngine.getGameStatus().getString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Status(status, gameGrid, entityList);
    }

    public Status(String status, String serializedGameGrid, String serializedEntityList) {
        this.status = status;
        this.serializedGameGrid = serializedGameGrid;
        this.serializedEntityList = serializedEntityList;
    }

    public String getSerializedGameGrid() {
        return serializedGameGrid;
    }

    public String getSerializedEntityList() {
        return serializedEntityList;
    }

    public String getStatus() {
        return status;
    }


}
