import gameobjects.GameEngine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * EngineTest
 *
 * @author stefano
 * @version 1.0.0
 */
public class EngineTest {

    @Test
    public void testLevelLoading() {
        GameEngine engine = new GameEngine();
        engine.loadLevel("level1.txt");
    }

    @Test
    public void testSerialize() {
        GameEngine engine = new GameEngine();
        engine.loadLevel("level1.txt");

        boolean passed = false;
        try {
            engine.serialize();
            passed = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(passed);
    }
}
