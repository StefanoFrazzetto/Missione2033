package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class LevelLoader {
    public static Level loadLevel(String levelName) {
        Objects.requireNonNull(levelName);
        InputStream resourceAsStream = LevelLoader.class.getClassLoader().getResourceAsStream("/Levels/" + levelName + "txt");

        return loadLevel(resourceAsStream);
    }

    public static Level loadLevel(File file) throws FileNotFoundException {
        Objects.requireNonNull(file);

        return loadLevel(new FileInputStream(file));
    }

    public static Level loadLevel(InputStream stream) {
        Objects.requireNonNull(stream);

        LevelParser parser = new LevelParser(stream);

        parser.parse();

        return new Level(parser);
    }
}
