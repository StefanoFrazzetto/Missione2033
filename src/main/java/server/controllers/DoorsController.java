package server.controllers;

import game.LevelParser;
import game.entities.Entity;
import game.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.EntityList;

import java.io.IOException;
import java.util.List;

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
    public EntityList openDoors(@RequestParam(value="type") String type) throws IOException {
        gameEngine.openDoors(type.toUpperCase().charAt(0));

        return new EntityList(gameEngine.getEntityList());
    }

    @RequestMapping("/doors/close")
    public EntityList closeDoors(@RequestParam(value="type") String type) throws IOException {
        gameEngine.openDoors(type.toUpperCase().charAt(0));

        return new EntityList(gameEngine.getEntityList());
    }
}