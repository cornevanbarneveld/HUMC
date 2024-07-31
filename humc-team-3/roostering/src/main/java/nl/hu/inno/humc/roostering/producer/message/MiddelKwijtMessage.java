package nl.hu.inno.humc.roostering.producer.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class MiddelKwijtMessage {
    public final UUID uuid;
    public Long middelId;


    protected MiddelKwijtMessage() {
        this.uuid = UUID.randomUUID();
    }

    public MiddelKwijtMessage(Long middelId) {
        this();
        this.middelId = middelId;
    }
}
