package server.controllers;

import gameobjects.Entity;
import gameobjects.GameEngine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Application;
import server.responses.Status;
import utils.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
    public EntityListResponse entities() throws IOException {
        GameEngine gameEngine = Application.getEngine();

        return new EntityListResponse(gameEngine.getEntityList());
    }

    /**
     * Class to handle the response with only the Entity list.
     */
    public class EntityListResponse {
        public String getSerializedEntityList() {
            return serializedEntityList;
        }

        private final String serializedEntityList;

        public EntityListResponse(List<Entity> entityList) throws IOException {
            this.serializedEntityList = Serializer.toString((Serializable) entityList);
        }
    }
}
