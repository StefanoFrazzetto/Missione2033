import gameobjects.Level;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * LevelTest
 *
 * @author stefano
 * @version 1.0.0
 */
public class LevelTest {

    @Test
    public void testInit() {
        Level level = new Level(5);
        Assert.assertNotNull(level);
    }

    @Test
    public void testLevelParsing() {
        Level level = new Level(5);
        List<String> rawLevel = new ArrayList<>();
        rawLevel.add("WWWWWW");
        rawLevel.add("WWWWWW");
        rawLevel.add("WWPWWW");
        rawLevel.add("WWWWWW");
        rawLevel.add("WWWWWW");

        level.parseRawLevel(rawLevel);

        Assert.assertNotNull(level.getGameGrid());
    }
}
