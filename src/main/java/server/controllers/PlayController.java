package server.controllers;

import gameobjects.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Message;
import utils.Serializer;

import java.io.IOException;

/**
 * PlayController
 *
 * This should return the map, the objects in the map, etc.
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class PlayController {

    @RequestMapping("/play")
    public Message message() {

        GameEngine gameEngine = Application.getEngine();
        gameEngine.initializeGame();

        try {
            return new Message(Serializer.toString(gameEngine));
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("Error while initializing the game");
        }
    }
}
