package server;

import gameobjects.GameEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static GameEngine engine;

    public static void main(String[] args) {
        engine = new GameEngine();

        SpringApplication.run(Application.class, args);
    }

}