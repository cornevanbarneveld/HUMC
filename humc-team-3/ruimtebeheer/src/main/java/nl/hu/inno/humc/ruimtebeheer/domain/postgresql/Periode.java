package nl.hu.inno.humc.ruimtebeheer.domain.postgresql;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class Periode implements Serializable  {

    private LocalDateTime startDatum;

    private LocalDateTime eindDatum;

    public Periode(LocalDateTime registratieDatum, LocalDateTime eindDatum) {
        this.startDatum = registratieDatum;
        this.eindDatum = eindDatum;
    }

    protected Periode() {

    }

    //Als ik het isProductGeldig noem krijg ik de error: "for property-based access both getter and setter should be present"
    //Hibernate denkt dan dat het een getter is en het naar de database geschreven moet worden, daarom geen goede camelCase
    public boolean isperiodeVerlopen() {
        return eindDatum.isAfter(LocalDateTime.now());
    }

    public LocalDateTime getstartDatum() {
        return startDatum;
    }

    public LocalDateTime getEindDatum() {
        return eindDatum;
    }

    private void setstartDatum(LocalDateTime startDatum) {
        this.startDatum = startDatum;
    }

    private void setEindDatum(LocalDateTime eindDatum) {
        this.eindDatum = eindDatum;
    }

    public boolean overlaptPeriode(Periode periode) {
        return periode.getstartDatum().isBefore(eindDatum) && periode.getEindDatum().isAfter(startDatum);
    }

    public static boolean overlappenPeriodes(Periode periode1, Periode periode2) {
        return periode1.getstartDatum().isBefore(periode2.getEindDatum()) && periode1.getEindDatum().isAfter(periode2.getstartDatum());
    }

}
