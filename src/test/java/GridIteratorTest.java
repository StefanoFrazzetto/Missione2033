import game.Grid;
import game.interfaces.Griddable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GridIteratorTest {
    @Test
    public void test1() {
        Griddable entity = () -> false;

        Grid<Griddable> grid = new Grid<>(entity.getClass(), 30, 30);

        grid.put(entity, 20, 20);

        ArrayList<Griddable> foundEntities = new ArrayList<>();

        grid.forEach((entity1, x, y) -> {
            if (entity1 != null)
                foundEntities.add(entity);
        });

        System.out.println(foundEntities.size());

        Assert.assertTrue(foundEntities.size() == 1);

        Assert.assertEquals(foundEntities.get(0), entity);
    }

}
