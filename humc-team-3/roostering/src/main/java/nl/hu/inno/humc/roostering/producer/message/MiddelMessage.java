package nl.hu.inno.humc.roostering.producer.message;


import com.fasterxml.jackson.core.JsonProcessingException;


import java.time.LocalDateTime;

public class MiddelMessage {
    public Long middelId;
    public Long ruimteId;
    public LocalDateTime beginDatum;
    public LocalDateTime eindDatum;


    protected MiddelMessage() {

    }

    public MiddelMessage(Long middelId, Long ruimteId, LocalDateTime beginDatum, LocalDateTime eindDatum) throws JsonProcessingException {
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;
        this.middelId = middelId;
        this.ruimteId = ruimteId;
    }
}
