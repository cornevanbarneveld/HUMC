package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte.locatie;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class Locatie implements Serializable {
    @Embedded
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
