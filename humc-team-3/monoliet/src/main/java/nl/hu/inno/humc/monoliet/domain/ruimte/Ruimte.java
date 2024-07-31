package nl.hu.inno.humc.monoliet.domain.ruimte;

import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.Inventaris;
import nl.hu.inno.humc.monoliet.domain.ruimte.locatie.Locatie;


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

    public Ruimte(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam) {
        this.locatie = new Locatie(postcode,straatnaam,huisnummer,ruimtenummer,halnummer);
        this.naam = naam;
        this.inventaris = new Inventaris();
    }

    public Ruimte() {}

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
}
