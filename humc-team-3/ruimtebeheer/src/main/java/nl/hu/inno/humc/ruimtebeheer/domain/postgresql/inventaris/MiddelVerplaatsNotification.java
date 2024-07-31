package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.inventaris;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
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

    protected MiddelVerplaatsNotification() {

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setMiddelId(Long middelId) {
        this.middelId = middelId;
    }

    public void setRuimteIdTo(Long ruimteIdTo) {
        this.ruimteIdTo = ruimteIdTo;
    }

    public void setBeginDatum(LocalDateTime beginDatum) {
        this.beginDatum = beginDatum;
    }

    public void setEindDatum(LocalDateTime eindDatum) {
        this.eindDatum = eindDatum;
    }
}
