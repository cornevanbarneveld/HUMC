package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte;

import jakarta.persistence.*;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.inventaris.Inventaris;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.Periode;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte.locatie.Locatie;

import java.time.LocalDateTime;


@Entity
public class Ruimte {
    @GeneratedValue
    @Id
    private Long id;
    @Embedded
    private Locatie locatie;
    private String naam;
    @OneToOne(cascade = CascadeType.ALL)
    private Inventaris inventaris;

    @Embedded
    private Periode onderhoudsPeriode;

    public Ruimte(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam) {
        this.locatie = new Locatie(postcode,straatnaam,huisnummer,ruimtenummer,halnummer);
        this.naam = naam;
        this.inventaris = new Inventaris();
    }

    protected Ruimte() {}

    public String getNaam() {
        return naam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public Inventaris getInventaris() {
        return inventaris;
    }

    public Long getId() {
        return id;
    }

    public void setOnderhoudsPeriode(LocalDateTime registratieDatum, LocalDateTime eindDatum) {
        this.onderhoudsPeriode = new Periode(registratieDatum,eindDatum);
    }

    public Periode getOnderhoudsPeriode() {
        return onderhoudsPeriode;
    }

    public boolean isRuimteBeschikbaarInPeriode(Periode periode) {
        if (onderhoudsPeriode != null) {
            return !this.onderhoudsPeriode.overlaptPeriode(periode);
        }
        return true;
    }
}
