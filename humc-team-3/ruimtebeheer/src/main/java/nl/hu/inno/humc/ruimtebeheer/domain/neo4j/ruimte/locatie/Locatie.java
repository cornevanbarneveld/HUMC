package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.locatie;

import org.springframework.data.neo4j.core.schema.*;

@Node
public class Locatie {

    @GeneratedValue
    @RelationshipId
    private Long id;
    @Relationship(type = "pcode", direction = Relationship.Direction.OUTGOING)
    private Postcode postcode;
    private String straatnaam;
    private int huisnummer;
    private int ruimtenummer;
    private int halnummer;


    public Locatie(String postcode, String straatnaam, int huisnummer, int ruimtenummer, int halnummer) {
        this.postcode = new Postcode(postcode);
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.ruimtenummer = ruimtenummer;
        this.halnummer = halnummer;
    }

    public Postcode getPostcode() {
        return postcode;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public int getRuimtenummer() {
        return ruimtenummer;
    }

    public int getHalnummer() {
        return halnummer;
    }

    protected Locatie() {

    }

}
