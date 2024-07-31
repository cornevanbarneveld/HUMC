package nl.hu.inno.humc.onderzoek.domain.postgress.onderzoek;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.onderzoek.domain.exception.InvalidDateException;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class OnderzoekPeriode implements Serializable {
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
