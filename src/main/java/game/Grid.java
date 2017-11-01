package game;

import com.sun.istack.internal.Nullable;
import game.interfaces.Griddable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Grid
 *
 * @author Stefano Frazzetto
 * @authro Vittorio Iocolano
 * @version 1.0.0
 */
public class Grid<T extends Griddable> implements Iterable<T>, Serializable, Cloneable {

    /**
     * The class of the objects contained in this grid
     */
    private final Class<? extends Griddable> klass;

    /** The grid width */
    private final int WIDTH;

    /** The grid height */
    private final int HEIGHT;

    /**
     * The array containing the objects
     */
    private T[][] array;

    @SuppressWarnings("unchecked")
    public Grid(Class<? extends T> klass, int width, int height) {
        this.klass = klass;
        WIDTH = width;
        HEIGHT = height;

        array = (T[][]) Array.newInstance(klass, WIDTH, HEIGHT);
    }

    @SuppressWarnings("unchecked")
    public Grid<T> clone() {
        Grid<T> grid = new Grid<>((Class<? extends T>) klass, WIDTH, HEIGHT);
        grid.array.clone();

        return grid;
    }

    /**
     * Get the grid width.
     *
     * @return
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Get the grid height.
     *
     * @return
     */
    public int getHeight() {
        return HEIGHT;
    }

    public boolean isOutOfBounds(int x, int y) {
        return (x > WIDTH || x < 0 || y > HEIGHT || y < 0);
    }

    /**
     * Put an object at x, y.
     *
     * @param object
     * @param x
     * @param y
     */
    public void put(T object, int x, int y) {
        Objects.requireNonNull(object);

        array[y][x] = object;
    }

    /**
     * Return the object contained at x, y.
     *
     * @param x
     * @param y
     * @return
     */
    public T get(int x, int y) {
        return array[y][x];
    }

    /**
     * Remove the object at x, y from the grid.
     *
     * @param x
     * @param y
     * @return the object removed from the grid
     */
    public T remove(int x, int y) {
        T object = array[y][x];
        array[y][x] = null;

        return object;
    }

    public String printGrid() {
        StringBuilder sb = new StringBuilder(array.length);

        for (T[] objects : array) {
            for (T object : objects) {
                sb.append(object);
            }

            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Return true if the object at x, y is blocking movement and/or sight.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isBlocked(int x, int y) {
        T t = get(x, y);

        return t == null || t.isBlocking();
    }

    @Override
    public Iterator<T> iterator() {
        return new GridIterator();
    }

    public void forEach(GridConsumer<T> action) {
        GridIterator iterator = (GridIterator) iterator();

        while (iterator.hasNext()){
            action.accept(iterator.next(), iterator.getX(), iterator.getY());
        }
    }

    public interface GridConsumer<T> {
        void accept(@Nullable T t, int x, int y);
    }

    /**
     * GridIterator is used to iterate through a Grid.
     */
    public class GridIterator implements Iterator<T> {
        int x = -1;
        int y = 0;

        /**
         * @return the current row
         */
        public int getX() {
            return x;
        }

        /**
         * @return the current column
         */
        public int getY() {
            return y;
        }

        @Override
        public boolean hasNext() {
            return !(x == WIDTH - 1 && y == HEIGHT - 1);
        }

        @Override
        public T next() {
            if (x < WIDTH - 1) {
                x++;
            } else {
                x = 0;
                y++;
            }

            return get(x, y);
        }
    }
}
