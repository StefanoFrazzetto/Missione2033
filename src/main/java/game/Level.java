package game;

import game.entities.*;
import game.gridobjects.Door;
import game.gridobjects.Exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Level
 *
 * @author stefano
 * @version 1.0.0
 */
public class Level {

    private final Grid<LevelParser> gameGrid;

    private final List<LevelParser> gameObjectList;

    public Level(InputStream stream) {

    }



    public String toString() {
        return gameGrid.toString();
    }

    public Grid<LevelParser> getGameGrid() {
        return gameGrid;
    }

    public List<LevelParser> getGameObjectList() {
        return gameObjectList;
    }
}
