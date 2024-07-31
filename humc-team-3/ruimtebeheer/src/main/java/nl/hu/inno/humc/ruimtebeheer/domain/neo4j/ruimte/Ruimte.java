package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte;

import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.inventaris.Inventaris;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.Periode;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.locatie.Locatie;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;

@Node
public class Ruimte {
    @GeneratedValue
    @Id
    private Long id;
    private String naam;
    @Relationship(type = "ONDERHOUD", direction = Relationship.Direction.OUTGOING)
    private Periode onderhoudsPeriode;

    @Relationship(type = "locatieRuimte", direction = Relationship.Direction.OUTGOING)
    private Locatie locatieRuimte;

    @Relationship(type = "inventarisRuimte", direction = Relationship.Direction.OUTGOING)
    private Inventaris inventarisRuimte;

    public Ruimte(String naam) {
        this.naam = naam;
    }

    public Ruimte(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam) {
        Locatie locatie = new Locatie(postcode,straatnaam,huisnummer,ruimtenummer,halnummer);
        this.locatieRuimte = locatie;
        this.naam = naam;
        this.inventarisRuimte = new Inventaris();
    }
    protected Ruimte() {}

    public String getNaam() {
        return naam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locatie getLocatie() {
        return locatieRuimte;
    }


    public Inventaris getInventaris() {
        return inventarisRuimte;
    }

    public Long getId() {
        return id;
    }

    public void setOnderhoudsPeriode(LocalDateTime startDatum, LocalDateTime eindDatum) {
        Periode periode = new Periode(startDatum, eindDatum);
        this.onderhoudsPeriode = periode;
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
