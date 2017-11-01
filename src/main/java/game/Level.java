package game;

import game.gridobjects.Floor;
import game.gridobjects.GridObject;

import java.io.Serializable;

/**
 * Level
 *
 * @author stefano
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
