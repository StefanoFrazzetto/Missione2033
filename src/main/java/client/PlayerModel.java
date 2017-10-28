package client;

import gameobjects.GameGrid;
import gameobjects.GameObject;

public abstract class PlayerModel {
    protected GameGrid gameGrid;

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void initGame() {
        // load the game grid async?

        // HERE WE SHOULD LOAD THE MAP

        // TEMP MODEL HERE
        gameGrid = new GameGrid(50, 50);
        gameGrid.putGameObjectAt(0, 0, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(1, 1, GameObject.fromChar('P'));
        gameGrid.putGameObjectAt(2, 2, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(3, 3, GameObject.fromChar('W'));
        gameGrid.putGameObjectAt(4, 4, GameObject.fromChar('W'));
    }
}
