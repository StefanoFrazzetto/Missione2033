package gameobjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Level
 *
 * @author stefano
 * @version 1.0.0
 */
public class Level {

    private final GameGrid gameGrid;

    private final List<Entity> entityList;

    public Level(InputStream stream) {
        List<String> rawLevel = parseStreamContent(stream);
        gameGrid = new GameGrid(rawLevel.size(), rawLevel.get(0).length());
        entityList = new ArrayList<>();

        parseRawLevel(rawLevel);
    }

    /**
     * FOR TESTING
     */
    public Level(int size) {
        gameGrid = new GameGrid(size, size);
        entityList = new ArrayList<>();
    }

    /**
     * Parse the stream from a level file.
     *
     * @param stream the stream of data
     * @return a raw level
     */
    private List<String> parseStreamContent(InputStream stream) {
        List<String> rawLevel = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            while (true) { // Read the file line by line
                String line = reader.readLine();

                // Break the loop if EOF is reached
                if (line == null) {
                    break;
                }

                // Trim the line, transform to uppercase, and add it to the Array
                line = line.trim();
                line = line.toUpperCase();
                rawLevel.add(line);
            } // END - While loop

        } catch (IOException | NullPointerException e) {
            // Cannot open/load the file
            e.printStackTrace();
        }

        return rawLevel;
    }

    /**
     * Parse the raw level into our objects.
     * @param rawLevel the raw level
     */
    public void parseRawLevel(List<String> rawLevel) {
        // Loop over the List
        for (int row = 0; row < rawLevel.size(); row++) {

            // Loop over the string one char at a time because it should be the fastest way:
            // http://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
            for (int column = 0; column < rawLevel.get(row).length(); column++) {
                // Get the GameObject from the char
                GameObject currentGameObject = GameObject.fromChar(rawLevel.get(row).charAt(column));

                if ( // static object
                        currentGameObject == GameObject.FLOOR ||
                                currentGameObject == GameObject.WALL
                        ) {
                    gameGrid.putGameObjectAt(row, column, currentGameObject);
                } else { // entity

                    Entity entity;
                    switch (currentGameObject) {
                        case EXIT:
                            entity = new Exit();
                            break;

                        case DOOR_X:
                        case DOOR_O:
                        case DOOR_P:
                        case DOOR_R:
                        case DOOR_S:
                        case DOOR_T:
                            entity = new Door(currentGameObject, false);
                            break;

                        case PLAYER:
                            entity = new Agent();
                            break;

                        default:
                        case ENEMY:
                            entity = new Enemy();
                            break;
                    }

                    entity.setCoordinates(row, column);
                    gameGrid.putGameObjectAt(row, column, GameObject.FLOOR);

                    entityList.add(entity);
                }
            } // END- String loop
        } // END - List loop

        System.out.println(gameGrid);
    }

    public String toString() {
        return gameGrid.toString();
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }
}
