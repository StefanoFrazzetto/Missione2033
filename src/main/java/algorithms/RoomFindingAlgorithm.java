package algorithms;

import game.Level;
import game.LevelParser;
import game.gridobjects.Door;
import game.entities.Entity;
import game.GameGrid;
import game.gridobjects.GridObject;
import game.gridobjects.Wall;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class RoomFindingAlgorithm {
    public static Rectangle2D findRectangleRoom(Level level, int x, int y) {

        int startx = x;
        int starty = y;

        int minx = 0;
        int miny = 0;
        int maxx = 0;
        int maxy = 0;

        // GOES UP 'N  DOWN

        for (; !level.getGameGrid().isBlocked(x, y); y--) if (y <= 0) break;

        miny = y;
        y = starty;


        for (; !level.getGameGrid().isBlocked(x, y); y++) if (y >= level.getGameGrid().getHeight() - 1) break;
        maxy = y;
        y = starty;

        // GOES RIGHT AND LEFT

        for (; !level.getGameGrid().isBlocked(x, y); x++) if (x >= level.getGameGrid().getWidth() - 1) break;
        maxx = x;
        x = startx;

        for (; !level.getGameGrid().isBlocked(x, y); x--) if (y <= 0) break;
        minx = x;

        return new Rectangle2D(minx, miny, maxx - minx, maxy - miny);
    }
}
