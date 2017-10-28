package server.responses;

/**
 * Message
 *
 * @author stefano
 * @version 1.0.0
 */
public class Message {
    private final String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
