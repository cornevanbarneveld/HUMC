package nl.hu.inno.humc.ruimtebeheer.producer.message;


import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BeschikbareItemsMessage {
    private final UUID uuid;
    private Map<String, Integer> beschikbareMiddelen;
    private Map<String, Integer> nietBeschikbareMiddelen;
    private Long ruimteId;
    private boolean succes;

    public BeschikbareItemsMessage(Map<String, Integer> beschikbareMiddelen, Map<String, Integer> nietBeschikbareMiddelen, Long ruimteId , boolean succes) {
        this.beschikbareMiddelen = beschikbareMiddelen;
        this.nietBeschikbareMiddelen = nietBeschikbareMiddelen;
        this.ruimteId = ruimteId;
        this.succes = succes;
        this.uuid = UUID.randomUUID();
    }

    public Map<String, Integer> getBeschikbareMiddelen() {
        return beschikbareMiddelen;
    }
    public Map<String, Integer> getNietBeschikbareMiddelen() {
        return nietBeschikbareMiddelen;
    }
    public UUID getUuid() {
        return uuid;
    }
    public Long getRuimteId() {return ruimteId;}
    public boolean isSucces() {return succes;}
}
