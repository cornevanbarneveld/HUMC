package nl.hu.inno.humc.ruimtebeheer.producer.message;

import java.util.UUID;

public class MiddelAfwezigMessage {

    private final UUID uuid;

    private Long middelId;


    public MiddelAfwezigMessage(Long middelId) {
        this.middelId = middelId;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getMiddelId() {
        return middelId;
    }
}
