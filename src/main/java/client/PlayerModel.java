package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import game.entities.Entity;
import game.GameGrid;
import utils.Serializer;

import java.io.IOException;
import java.util.List;

public abstract class PlayerModel {
    protected GameGrid gameGrid;
    protected List<Entity> entityList;

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void updateGridGrid() throws UnirestException, IOException, ClassNotFoundException {
        HttpResponse<JsonNode> jsonResponseStatus = Unirest.get(Main.getHost() + "/status")
                .header("accept", "application/json")
                .asJson();

        String serializedGameGrid = jsonResponseStatus.getBody().getObject().getString("serializedGameGrid");

        gameGrid = (GameGrid) Serializer.fromString(serializedGameGrid);

        assert gameGrid != null;
    }

    public void updateEntityList() throws UnirestException, IOException, ClassNotFoundException {
        long startTime = System.currentTimeMillis();

        HttpResponse<JsonNode> jsonResponseStatus = Unirest.get(Main.getHost() + "/status/entities")
                .header("accept", "application/json")
                .asJson();

        updateEntityListFromJsonResponse(jsonResponseStatus);

        long endTime = System.currentTimeMillis();

        System.out.println(startTime - endTime + " ms to update entity list");
    }

    protected void updateEntityListFromJsonResponse(HttpResponse<JsonNode> jsonResponseStatus) throws UnirestException, IOException, ClassNotFoundException {
        String serializedEntityList = jsonResponseStatus.getBody().getObject().getString("serializedEntityList");

        //noinspection unchecked
        entityList = (List<Entity>) Serializer.fromString(serializedEntityList);
    }

    public void initGame() throws UnirestException {
        long startTime = System.currentTimeMillis();

        HttpResponse<JsonNode> jsonResponse = Unirest.get(Main.getHost() + "/play")
                .header("accept", "application/json")
                .asJson();

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(estimatedTime + " ms elapsed for the init request.");

        String serializedGameGrid = jsonResponse.getBody().getObject().getString("serializedGameGrid");
        String serializedEntityList = jsonResponse.getBody().getObject().getString("serializedEntityList");

        try {
            gameGrid = (GameGrid) Serializer.fromString(serializedGameGrid);

            assert gameGrid != null;

            //noinspection unchecked
            entityList = (List<Entity>) Serializer.fromString(serializedEntityList);

            assert entityList != null;

            // We need some heavier validation here
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
