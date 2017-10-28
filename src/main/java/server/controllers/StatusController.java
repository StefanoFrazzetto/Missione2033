package server.controllers;

import gameobjects.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Status;

import java.io.IOException;

/**
 * StatusController
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class StatusController {
    @RequestMapping("/status")
    public Status status() {

        GameEngine gameEngine = Application.getEngine();

        try {
            return Status.fromGameEngine(gameEngine);
        } catch (IOException e) {
            e.printStackTrace();
            return new Status("error", null, null);
        }
    }
}
