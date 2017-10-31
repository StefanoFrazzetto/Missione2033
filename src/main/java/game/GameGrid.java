package game;

import java.awt.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

/**
 * GameGrid class can be used to create a 2D grid containing GameObjects.
 */
public class GameGrid implements Iterable, Serializable {

    /**
     * The number of columns
     */
    public final int COLUMNS;
    /**
     * The number of rows
     */
    public final int ROWS;

    /**
     * The grid containing every GameObject
     */
    private GameObject[][] gameObjects;

    /**
     * Create the grid using columns and rows to set the maximum size.
     *
     * @param columns the number of columns
     * @param rows    the number of rows
     */
    public GameGrid(int columns, int rows) {
        COLUMNS = columns;
        ROWS = rows;

        // Initialize the array
        gameObjects = new GameObject[COLUMNS][ROWS];
    }

    /**
     * Return the point located at a distance delta from a starting GameObject.
     *
     * @param sourceLocation the source point
     * @param delta          the distance between the reference GameObject and the target point
     * @return the target point at a distance delta from sourceLocation.
     */
    static Point translatePoint(Point sourceLocation, Point delta) {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }

    /**
     * Return the size of this grid as {@link Dimension}.
     *
     * @return a {@link Dimension} GameObject that indicates the size of this grid
     */
    public Dimension getDimension() {
        return new Dimension(COLUMNS, ROWS);
    }

    /**
     * Gets the GameObject at delta distance from the origin GameObject.
     *
     * @param source the source GameObject location
     * @param delta  the distance from source
     * @return the target GameObject
     */
    GameObject getTargetFromSource(Point source, Point delta) {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * Get the GameObject positioned at (x, y).
     *
     * @param column the row of the GameObject
     * @param row    the column of the GameObject
     * @return GameObject
     * @throws ArrayIndexOutOfBoundsException if the coordinates are outside the grid bounds
     */
    public GameObject getGameObjectAt(int column, int row) throws ArrayIndexOutOfBoundsException {
        if (isPointOutOfBounds(column, row)) {
            throw new ArrayIndexOutOfBoundsException("The point [" + column + ":" + row + "] is outside the map.");
        }

        return gameObjects[column][row];
    }

    /**
     * Get an GameObject located at the chosen {@link Point}
     *
     * @param point the point where the GameObject should be found
     * @return GameObject if the the GameObject is found || null if the point is null
     */
    public GameObject getGameObjectAt(Point point) {
        Objects.requireNonNull(point);

        return getGameObjectAt((int) point.getX(), (int) point.getY());
    }

    /**
     * Remove a {@link GameObject} from the grid by setting it to null.
     *
     * @param point the point containing the object to remove
     * @return boolean  true if it was possible to remove the GameObject, false otherwise
     */
    public boolean removeGameObjectAt(Point point) {
        Objects.requireNonNull(point);

        return putGameObjectAt(point, null);
    }


    /**
     * Put a {@link GameObject} into the specified location (x, y).
     *
     * @param x          the x coordinate
     * @param y          the y coordinate
     * @param gameObject the gameObject to be put into the array
     * @return true if the operation is successful, false otherwise
     */
    public boolean putGameObjectAt(int x, int y, GameObject gameObject) {
        if (isPointOutOfBounds(x, y)) {
            return false;
        }

        gameObjects[x][y] = gameObject;
        return gameObjects[x][y].equals(gameObject);
    }

    /**
     * Puts a {@link GameObject} into the specified point.
     *
     * @param p          the point where the GameObject will be put into
     * @param gameObject the GameObject to be put into the array
     * @return true if the operation is successful, false otherwise.
     */
    public boolean putGameObjectAt(Point p, GameObject gameObject) {
        Objects.requireNonNull(p);

        return putGameObjectAt((int) p.getX(), (int) p.getY(), gameObject);
    }

    /**
     * Check if a point is outside the grid.
     *
     * @param x the x position on the grid
     * @param y the y position on the grid
     * @return true if the point is outside the grid, false otherwise.
     */
    private boolean isPointOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
    }

    /**
     * Check if a point is outside the grid.
     *
     * @param p the point to be checked
     * @return true if the point is outside the grid, false otherwise.
     */
    private boolean isPointOutOfBounds(Point p) {
        return isPointOutOfBounds(p.x, p.y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(gameObjects.length);

        for (GameObject[] gameObject : gameObjects) {
            for (GameObject aGameObject : gameObject) {
                sb.append(aGameObject.getCharSymbol());
            }

            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Return an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<GameObject> iterator() {
        return new GridIterator();
    }

    /**
     * GridIterator provides the interface to iterate through the grid containing the {@link GameObject}s.
     *
     * @see Iterator
     */
    public class GridIterator implements Iterator<GameObject> {
        int column = -1;
        int row = 0;

        /**
         * @return the current row
         */
        public int getRow() {
            return row;
        }

        /**
         * @return the current column
         */
        public int getColumn() {
            return column;
        }

        @Override
        public boolean hasNext() {
            return !(column == COLUMNS - 1 && row == ROWS - 1);
        }

        @Override
        public GameObject next() {
            if (column < COLUMNS - 1) {
                column++;
            } else {
                column = 0;
                row++;
            }

            return getGameObjectAt(column, row);
        }
    }
}