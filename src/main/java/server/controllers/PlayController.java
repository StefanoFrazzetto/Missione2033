package server.controllers;

import game.Direction;
import game.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.EntityList;
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

    GameEngine gameEngine = Application.getEngine();

    @RequestMapping("/play")
    public Status status() {

        gameEngine.initializeGame();


        return Status.fromGameEngine(gameEngine);
    }

    @RequestMapping("/play/move")
    public EntityList move(@RequestParam(value = "d") String direction) throws IOException {

        Direction dir = Direction.fromCode(direction.charAt(0));

        gameEngine.handleMovement(dir);

        return new EntityList(gameEngine.getEntityList());
    }

    @RequestMapping("/action/attack")
    public EntityList openDoors(@RequestParam(value="direction") String direction) throws IOException {

        GameEngine gameEngine = Application.getEngine();
        Direction dir = Direction.fromCode(direction.charAt(0));

        gameEngine.attack(gameEngine.getAgent(), dir);

        return new EntityList(gameEngine.getEntityList());
    }
}
