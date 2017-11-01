package client;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Player2Controller extends PlayerController {
    public Player2Controller() {
        Platform.runLater(() -> {
            model = new Player2Model();

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
            try {
                ((Player2Model) model).openDoors(event.getText().charAt(0));
                draw();
            } catch (IOException | ClassNotFoundException | UnirestException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException ignored){}
        };
    }

    @Override
    protected boolean showCharacters() {
        return false;
    }

    @Override
    public int getRectSize() {
        return 17;
    }
}
