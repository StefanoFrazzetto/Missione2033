package server.controllers;

import game.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.EntityList;
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

        return Status.fromGameEngine(gameEngine);
    }

    @RequestMapping("/status/entities")
    public EntityList entities() throws IOException {
        GameEngine gameEngine = Application.getEngine();

        return new EntityList(gameEngine.getEntityList());
    }
}
