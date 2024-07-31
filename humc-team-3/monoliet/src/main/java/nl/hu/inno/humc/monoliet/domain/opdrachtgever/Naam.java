package nl.hu.inno.humc.monoliet.domain.opdrachtgever;

import jakarta.persistence.Embeddable;

@Embeddable
public class Naam {
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;

    public Naam(String voornaam, String achternaam, String tussenvoegsel) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
    }

    public Naam() {

    }
}
