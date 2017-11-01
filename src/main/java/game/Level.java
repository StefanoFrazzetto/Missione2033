package game;

import game.entities.Entity;
import game.gridobjects.Floor;
import game.gridobjects.GridObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Level
 *
 * @author Stefano Frazzetto
 * @author Vittorio Iocolano
 * @version 1.0.0
 */
public class Level implements Serializable {

    private final Grid<GridObject> gameGrid;

    private final Grid<Floor> floorGrid;

    private final Entities entities;

    public Level(LevelParser parser) {
        if (!parser.isParsed())
            throw new IllegalStateException("LevelParser has not parsed the level.");

        this.gameGrid = parser.getObjectsGrid();
        this.floorGrid = parser.getFloorGrid();
        this.entities = parser.getEntities();

        assert gameGrid != null;
        assert floorGrid != null;
        assert entities != null;
    }

    public Entity rayCast(int x, int y, Direction direction) {
        Vector position = new Vector(x, y);
        Vector displacement = direction.getVector();

        position.sum(displacement);

        // get possible entities
        List<Entity> entitiesOnDirection = getEntitiesOnDirection(x, y, direction);

        while (!getGameGrid().isOutOfBounds(position.getX(), position.getY()) &&
                !getGameGrid().isBlocked(position.getX(), position.getY())) {

            // we should order the entities by direction (shoot through entities)
            for (Entity entity : entitiesOnDirection) {
                if (entity.getX() == position.getX() && entity.getY() == position.getY()) {
                    return entity;
                }
            }

            position.sum(displacement);
        }


        return null;
    }

    public List<Entity> getEntitiesOnDirection(int x, int y, Direction direction) {
        List<Entity> foundEntities = new ArrayList<>();
        Vector position = new Vector(x, y);
        Vector displacement = direction.getVector();

        for (Entity entity : entities) {
            boolean add;

            switch (direction) {
                case NORTH:
                    add = entity.getX() == x && entity.getY() < y;
                    break;
                case SOUTH:
                    add = entity.getX() == x && entity.getY() > y;
                    break;
                case EAST:
                    add = entity.getY() == y && entity.getX() > x;
                    break;
                case WEST:
                    add = entity.getY() == y && entity.getX() < x;
                    break;
                default:
                    add = false;
            }

            if (add)
                foundEntities.add(entity);

            position.sum(displacement);
        }

        return foundEntities;
    }

    public String toString() {
        return gameGrid.toString();
    }

    public Grid<GridObject> getGameGrid() {
        return gameGrid;
    }

    public Grid<Floor> getFloorGrid() {
        return floorGrid;
    }

    public Entities getEntities() {
        return entities;
    }
}
