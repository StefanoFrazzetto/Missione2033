package server.controllers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Message;

import java.util.concurrent.atomic.AtomicLong;

/**
 * PlayController
 *
 * This should return the map, the objects in the map, etc.
 *
 * TODO: fix this fucking ugly controller.
 *
 * @author stefano
 * @version 1.0.0
 */
@RestController
public class PlayController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/play")
    public Message message(@RequestParam(value="playerType", required = false) String playerType) {

        if (playerType == null) {
            return new Message("Welcome, player! A new game has been initialised for you!");
        }

        String message = "Greetings! Your player type is ";

        try {
            message += Application.engine.handlePlayerInit(playerType);
        } catch (InvalidArgumentException e) {
            message = "The player type is not valid!";
            e.printStackTrace();
        }

        return new Message(message);
    }
}
