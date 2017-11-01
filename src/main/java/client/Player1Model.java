package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import game.Direction;
import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.io.IOException;
import java.util.List;

public class Player1Model extends PlayerModel {
    public void movePlayer(Direction direction, Runnable callback) {
        Unirest.get(String.format("%s/play/move?d=%s", Main.getHost(), direction.getCode())).asJsonAsync(new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                if (callback != null)
                    Platform.runLater(callback);
            }

            @Override
            public void failed(UnirestException e) {
                if (callback != null)
                    Platform.runLater(callback);
            }

            @Override
            public void cancelled() {
                if (callback != null)
                    Platform.runLater(callback);
            }
        });
    }

    public void movePlayer(Direction direction) {
        movePlayer(direction, null);
    }

    public void movePlayerSync(Direction direction, Runnable callback)  {
        long startTime = System.currentTimeMillis();

        try {
            HttpResponse<JsonNode> response = Unirest.get(String.format("%s/play/move?d=%s", Main.getHost(), direction.getCode())).asJson();

            this.updateLevelFromJsonResponse(response);
        } catch (UnirestException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(estimatedTime + " ms to send the control.");

        Platform.runLater(callback);
    }

    public void shootSync(Direction direction){
        try {
            HttpResponse<JsonNode> response = Unirest.get(String.format("%s/action/attack?direction=%s", Main.getHost(), direction.getCode())).asJson();

            this.updateLevelFromJsonResponse(response);
        } catch (UnirestException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Point2D getPlayerPosition() {
        return new Point2D(0, 0);
    }

    public List<Point2D> getEnemiesPositions() {
        return null;
    }
}
