package server.controllers;

import algorithms.AStarPathFinder;
import algorithms.GameMap;
import algorithms.Path;
import algorithms.UnitMover;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.responses.Message;

import java.io.IOException;

/**
 * AStarlavistaController
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class AStarlavistaController {
    @RequestMapping("/astarlavista")
    public Message openDoors() throws IOException {

        AStarPathFinder aStarPathFinder = new AStarPathFinder(new GameMap(), 500, true);
        Path solution = aStarPathFinder.findPath(new UnitMover(), 1, 1, 4, 4);

        for (int i = 0; i < solution.getLength(); i++) {
            System.out.printf("[Step %d] %d, %d\n", i, solution.getStep(i).getX(), solution.getStep(i).getY());
        }

        return new Message("Sucuni!");
    }
}
