package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import game.Entities;
import game.Grid;
import game.Level;
import game.gridobjects.GridObject;
import utils.Serializer;

import java.io.IOException;

public abstract class PlayerModel {
    protected Level level;

    public Grid<GridObject> getGameGrid() {
        return level.getGameGrid();
    }

    public void updateGridGrid() throws UnirestException, IOException, ClassNotFoundException {
        HttpResponse<JsonNode> jsonResponseStatus = Unirest.get(Main.getHost() + "/status")
                .header("accept", "application/json")
                .asJson();

        String serializedLevel = jsonResponseStatus.getBody().getObject().getString("serializedLevel");

        level = (Level) Serializer.fromString(serializedLevel);

        assert level != null;
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
        Entities entities = (Entities) Serializer.fromString(serializedEntityList);

        level.getEntities().clear();
        level.getEntities().addAll(entities);
    }

    protected void updateLevelFromJsonResponse(HttpResponse<JsonNode> jsonResponseStatus) throws UnirestException, IOException, ClassNotFoundException {
        String serializedLevel = jsonResponseStatus.getBody().getObject().getString("serializedLevel");

        //noinspection unchecked
        level = (Level) Serializer.fromString(serializedLevel);

        assert level != null;
    }

    public void initGame() throws UnirestException {
        long startTime = System.currentTimeMillis();

        HttpResponse<JsonNode> jsonResponse = Unirest.get(Main.getHost() + "/play")
                .header("accept", "application/json")
                .asJson();

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(estimatedTime + " ms elapsed for the init request.");

        try {
            updateLevelFromJsonResponse(jsonResponse);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
