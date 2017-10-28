package server.controllers;

import gameobjects.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Status;
import utils.Serializer;

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
            return new Status(gameEngine.getStatus(), Serializer.toString(gameEngine));
        } catch (IOException e) {
            e.printStackTrace();
            return new Status("ERROR", null);
        }
    }
}
