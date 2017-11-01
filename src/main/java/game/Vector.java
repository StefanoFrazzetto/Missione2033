package game;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Vector
 *
 * @author Vittorio Iocolano
 * @version 1.0.0
 */
public class Vector {
    private int x = 0;
    private int y = 0;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void sum(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
    }

    public void subtract(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public double getMagnitude(){
        return sqrt(pow(x, 2) + pow(y, 2));
    }

    public Vector() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
