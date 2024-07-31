package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.inventaris;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Node
public class MiddelVerplaatsNotification {

    @GeneratedValue
    @Id
    private Long id;
    private Long middelId;
    private Long ruimteIdTo;
    private LocalDateTime beginDatum;
    private LocalDateTime eindDatum;

    public MiddelVerplaatsNotification(Long middelId, Long ruimteIdTo, LocalDateTime beginDatum, LocalDateTime eindDatum) {
        this.middelId = middelId;
        this.ruimteIdTo = ruimteIdTo;
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;
    }

    public Long getId() {
        return id;
    }

    public Long getMiddelId() {
        return middelId;
    }

    public Long getRuimteIdTo() {
        return ruimteIdTo;
    }

    public LocalDateTime getBeginDatum() {
        return beginDatum;
    }

    public LocalDateTime getEindDatum() {
        return eindDatum;
    }
}
