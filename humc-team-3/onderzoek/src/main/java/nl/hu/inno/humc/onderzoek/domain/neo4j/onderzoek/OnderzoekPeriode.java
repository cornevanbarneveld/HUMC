package nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek;

import nl.hu.inno.humc.onderzoek.domain.exception.InvalidDateException;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OnderzoekPeriode implements Serializable {
    @GeneratedValue
    @Id
    private Long id;
    private LocalDateTime begindatum;
    private LocalDateTime einddatum;

    public OnderzoekPeriode(LocalDateTime begindatum, LocalDateTime einddatum) {
        if (begindatum == null || einddatum == null) {
            throw new InvalidDateException();
        }
        if (begindatum.isAfter(einddatum) || begindatum.isEqual(einddatum)) {
            throw new InvalidDateException();
        }

        this.begindatum = begindatum;
        this.einddatum = einddatum;
    }

    public OnderzoekPeriode() {

    }

    public LocalDateTime getBegindatum() {
        return begindatum;
    }

    public LocalDateTime getEinddatum() {
        return einddatum;
    }

    public void setBegindatum(LocalDateTime begindatum) {
    }

    public void setEinddatum(LocalDateTime einddatum) {
    }
}
