package algorithms;

import game.GameEngine;
import server.Application;

/**
 * GameMap
 *
 * @author stefano
 * @version 1.0.0
 */
public class GameMap implements TileBasedMap {

    private GameEngine gameEngine;

    public GameMap() {
        gameEngine = Application.getEngine();
    }

    public GameMap(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public int getWidthInTiles() {
        return
                gameEngine.getGameGrid().COLUMNS;
    }

    @Override
    public int getHeightInTiles() {
        return gameEngine.getGameGrid().ROWS;
    }

    @Override
    public void pathFinderVisited(int x, int y) {

    }

    @Override
    public boolean blocked(Mover mover, double x, double y) {
        return !gameEngine.isNodeFree(x, y);
    }

    @Override
    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        return 0;
    }
}
