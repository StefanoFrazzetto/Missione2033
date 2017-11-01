package game.gridobjects;

/**
 * Wall
 *
 * @author stefano
 * @version 1.0.0
 */
public class Wall extends GridObject {
    @Override
    public boolean isBlocking() {
        return true;
    }
}
