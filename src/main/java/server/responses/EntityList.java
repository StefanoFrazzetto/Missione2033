package server.responses;

import game.entities.Entity;
import utils.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Class to handle the response with only the Entity list.
 *
 * @author stefano
 * @version 1.0.0
 */
public class EntityList {

    private final String serializedEntityList;

    public EntityList(List<Entity> entityList) throws IOException {
        this.serializedEntityList = Serializer.toString((Serializable) entityList);
    }

    public String getSerializedEntityList() {
        return serializedEntityList;
    }
}
