package nl.hu.inno.humc.monoliet.domain.middel;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Houdbaarheidsperiode {

    private LocalDateTime registratieDatum;

    private LocalDateTime houdbaarheidsDatum;

    public Houdbaarheidsperiode(LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum) {
        this.registratieDatum = registratieDatum;
        this.houdbaarheidsDatum = houdbaarheidsDatum;
    }

    public Houdbaarheidsperiode() {

    }

    //Als ik het isProductGeldig noem krijg ik de error: "for property-based access both getter and setter should be present"
    //Hibernate denkt dan dat het een getter is en het naar de database geschreven moet worden, daarom geen goede camelCase
    public boolean isproductGeldig() {
        return houdbaarheidsDatum.isAfter(LocalDateTime.now());
    }

    public LocalDateTime getRegistratieDatum() {
        return registratieDatum;
    }

    public LocalDateTime getHoudbaarheidsDatum() {
        return houdbaarheidsDatum;
    }

    public void setRegistratieDatum(LocalDateTime registratieDatum) {
        this.registratieDatum = registratieDatum;
    }

    public void setHoudbaarheidsDatum(LocalDateTime houdbaarheidsDatum) {
        this.houdbaarheidsDatum = houdbaarheidsDatum;
    }

}
