package nl.hu.inno.humc.monoliet.domain.ruimte.locatie;

import jakarta.persistence.*;

@Embeddable
public class Locatie {
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

    public Locatie() {

    }
}
