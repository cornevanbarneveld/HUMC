package nl.hu.inno.humc.roostering.producer.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseMessage {
    public final UUID uuid;
    public String message;
    public boolean error;



    protected ResponseMessage() {
        this.uuid = UUID.randomUUID();
    }

    public ResponseMessage(String message) {
        this.uuid = UUID.randomUUID();
        this.message = message;
    }

    public ResponseMessage(boolean error, String message) {
        this(message);
        this.error = error;
    }
}
