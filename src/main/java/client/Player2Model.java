package client;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Player2Model extends PlayerModel {
    public void openDoors(char code) throws UnirestException {
        Unirest.get(String.format("%s/doors/open?type=%C", Main.getHost(), code)).asJson();
    }
}
