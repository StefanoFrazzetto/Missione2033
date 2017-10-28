package server.controllers;

import gameobjects.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Status;

import java.io.IOException;

/**
 * PlayController
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class PlayController {

    @RequestMapping("/play")
    public Status status() {

        GameEngine gameEngine = Application.getEngine();
        gameEngine.initializeGame();

        try {
            return Status.fromGameEngine(gameEngine);
        } catch (IOException e) {
            e.printStackTrace();
            return new Status("error", null, null);
        }
    }
}
