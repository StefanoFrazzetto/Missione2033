package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gameobjects.GameGrid;
import gameobjects.GameObject;

public abstract class PlayerModel {
    protected GameGrid gameGrid;

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void initGame() throws UnirestException {
        // load the game grid async?

        // HERE WE SHOULD LOAD THE MAP

        long startTime = System.currentTimeMillis();

        HttpResponse<JsonNode> jsonResponse = Unirest.get(Main.getHost() + "/play")
                .header("accept", "application/json")
                .asJson();

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(estimatedTime + " ms elapsed for the init request.");

        System.out.println(jsonResponse.getBody().toString());

        // TEMP MODEL HERE
        gameGrid = new GameGrid(50, 50);
        gameGrid.putGameObjectAt(0, 0, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(1, 1, GameObject.fromChar('P'));
        gameGrid.putGameObjectAt(2, 2, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(3, 3, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(4, 4, GameObject.fromChar('W'));
    }
}
