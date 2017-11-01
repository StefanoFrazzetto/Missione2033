package server.controllers;

import game.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Status;

import java.io.IOException;

/**
 * DoorsController
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class DoorsController {

    GameEngine gameEngine = Application.getEngine();

    @RequestMapping("/doors/open")
    public Status openDoors(@RequestParam(value="type") String type) throws IOException {
        gameEngine.openDoors(type.toUpperCase().charAt(0));

        return Status.fromGameEngine(gameEngine);
    }

    @RequestMapping("/doors/close")
    public Status closeDoors(@RequestParam(value="type") String type) throws IOException {
        gameEngine.closeDoors(type.toUpperCase().charAt(0));

        return Status.fromGameEngine(gameEngine);
    }
}