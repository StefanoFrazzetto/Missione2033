package client;

import algorithms.RoomFindingAlgorithm;
import com.mashape.unirest.http.exceptions.UnirestException;
import gameobjects.Agent;
import gameobjects.Direction;
import gameobjects.Entity;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Player1Controller extends PlayerController {
    public Player1Controller() throws UnirestException {
        Platform.runLater(() -> {
            model = new Player1Model();
            try {
                model.initGame();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected EventHandler<KeyEvent> getKeyEventEventHandler() {
        return event -> {
            Direction d = null;

            switch (event.getText().toUpperCase()) {
                case "W":
                    d = Direction.NORTH;
                    break;
                case "S":
                    d = Direction.SOUTH;
                    break;
                case "D":
                    d = Direction.EAST;
                    break;
                case "A":
                    d = Direction.WEST;
                    break;
                default:
                    System.out.printf("[%s]%n", event.getCode());
            }

            if (d != null)
                ((Player1Model) model).movePlayerSync(d, () -> {
                    try {
                        model.updateEntityList();
                    } catch (UnirestException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    } finally {
                        draw();
                    }
                });
        };
    }

    @Override
    protected boolean showCharacters() {
        return true;
    }

    @Override
    public void draw() {
        Agent agent = null;

        for (Entity ent : model.entityList) {
            if (ent instanceof Agent)
                agent = (Agent) ent;
        }

        assert agent != null;

        Rectangle2D rectangleRoom = RoomFindingAlgorithm.findRectangleRoom(model.gameGrid, model.entityList, agent.getxCoordinate(), agent.getyCoordinate());

        super.draw((int) rectangleRoom.getMinX(), (int) rectangleRoom.getMinY(), (int) rectangleRoom.getWidth(), (int) rectangleRoom.getHeight());
    }

}