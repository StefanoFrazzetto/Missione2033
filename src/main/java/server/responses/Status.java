package server.responses;

import java.io.ObjectOutputStream;

/**
 * Status
 *
 * @author stefano
 * @version 1.0.0
 */
public class Status {
    private final String status;
    private final ObjectOutputStream objectOutputStream;

    public Status(String status, ObjectOutputStream objectOutputStream) {
        this.status = status;
        this.objectOutputStream = objectOutputStream;

    }

    public String getStatus() {
        return status;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
