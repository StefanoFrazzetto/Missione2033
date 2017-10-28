package server.responses;

/**
 * Status
 *
 * @author stefano
 * @version 1.0.0
 */
public class Status {
    private final String status;
    private final String serializedEngine;

    public Status(String status, String serializedEngine) {
        this.status = status;
        this.serializedEngine = serializedEngine;

    }

    public String getSerializedEngine() {
        return serializedEngine;
    }

    public String getStatus() {
        return status;
    }


}
