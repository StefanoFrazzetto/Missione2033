package client;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Player2Controller extends PlayerController {
    private Player2Model model;

    public Player2Controller() {
        model = new Player2Model();
    }

    @Override
    protected EventHandler<KeyEvent> getKeyEventEventHandler() {
        return null;
    }

    @Override
    protected boolean showCharacters() {
        return false;
    }
}
