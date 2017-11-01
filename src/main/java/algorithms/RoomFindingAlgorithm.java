package algorithms;

import game.LevelParser;
import game.gridobjects.Door;
import game.entities.Entity;
import game.GameGrid;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class RoomFindingAlgorithm {
    public static Rectangle2D findRectangleRoom(GameGrid grid, List<Entity> entities, double x, double y) {

        double startx = x;
        double starty = y;

        double minx = 0;
        double miny = 0;
        double maxx = 0;
        double maxy = 0;

        // GOES UP 'N  DOWN

        for (; !isBoundary(grid, entities, x, y); y--) if (y <= 0) break;

        miny = y;
        y = starty;


        for (; !isBoundary(grid, entities, x, y); y++) if (y >= grid.ROWS - 1) break;
        maxy = y;
        y = starty;

        // GOES RIGHT AND LEFT

        for (; !isBoundary(grid, entities, x, y); x++) if (x >= grid.COLUMNS - 1) break;
        maxx = x;
        x = startx;

        for (; !isBoundary(grid, entities, x, y); x--) if (y <= 0) break;
        minx = x;

        return new Rectangle2D(minx, miny, maxx - minx, maxy - miny);
    }

    private static boolean isBoundary(LevelParser object) {
        return object != LevelParser.FLOOR;


    }

    private static boolean isBoundary(GameGrid grid, List<Entity> entities, double x, double y) {
        return isThereAWallAt(grid, x, y) || isThereADoorAt(entities, x, y);
    }

    private static boolean isThereAWallAt(GameGrid grid, double x, double y) {
        return grid.getGameObjectAt(x, y) == LevelParser.WALL;
    }

    private static boolean isThereADoorAt(List<Entity> entities, double x, double y) {
        for (Entity entity : entities)
            if (entity instanceof Door) {
                if (entity.getX() == x && entity.getY() == y)
                    return !(((Door) entity).isOpen());
            }

        return false;
    }
}
