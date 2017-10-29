package algorithms;

import gameobjects.Door;
import gameobjects.Entity;
import gameobjects.GameGrid;
import gameobjects.GameObject;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class RoomFindingAlgorithm {
    public static Rectangle2D findRectangleRoom(GameGrid grid, List<Entity> entities, int x, int y) {

        int startx = x;
        int starty = y;

        int minx = 0;
        int miny = 0;
        int maxx = 0;
        int maxy = 0;

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

    private static boolean isBoundary(GameObject object) {
        return object != GameObject.FLOOR;


    }

    private static boolean isBoundary(GameGrid grid, List<Entity> entities, int x, int y) {
        return isThereAWallAt(grid, x, y) || isThereADoorAt(entities, x, y);
    }

    private static boolean isThereAWallAt(GameGrid grid, int x, int y) {
        return grid.getGameObjectAt(x, y) == GameObject.WALL;
    }

    private static boolean isThereADoorAt(List<Entity> entities, int x, int y) {
        for (Entity entity : entities)
            if (entity instanceof Door) {
                if (entity.getRow() == x && entity.getColumn() == y)
                    return !(((Door) entity).isOpen());
            }

        return false;
    }
}
