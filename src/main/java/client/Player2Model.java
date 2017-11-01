package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class Player2Model extends PlayerModel {
    public void openDoors(char code) throws UnirestException, IOException, ClassNotFoundException {
        HttpResponse<JsonNode> response = Unirest.get(String.format("%s/doors/open?type=%C", Main.getHost(), code)).asJson();

        updateLevelFromJsonResponse(response);
    }
}
