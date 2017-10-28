package server.controllers;

import gameobjects.Entity;
import gameobjects.GameEngine;
import gameobjects.GameObject;
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

        GameObject doorType = GameObject.fromChar(type.charAt(0));
        List<Entity> entityList = gameEngine.getEntityList();

        gameEngine.openDoors(doorType);

        return new EntityList(entityList);
    }

    @RequestMapping("/doors/close")
    public EntityList closeDoors(@RequestParam(value="type") String type) throws IOException {

        GameObject doorType = GameObject.fromChar(type.charAt(0));
        List<Entity> entityList = gameEngine.getEntityList();

        gameEngine.closeDoors(doorType);

        return new EntityList(entityList);
    }
}