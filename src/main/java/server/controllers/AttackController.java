package server.controllers;

import game.Direction;
import game.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.EntityList;

import java.io.IOException;

/**
 * AttackController
 *
 * @author stefano
 * @version 1.0.0
 */

@RestController
public class AttackController {
    @RequestMapping("/action/attack")
    public EntityList openDoors(@RequestParam(value="direction") String direction) throws IOException {

        GameEngine gameEngine = Application.getEngine();
        Direction dir = Direction.fromCode(direction.charAt(0));

        gameEngine.attack(gameEngine.getAgent(), dir);

        return new EntityList(gameEngine.getEntityList());
    }
}
