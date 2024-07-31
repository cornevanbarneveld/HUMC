package nl.hu.inno.humc.monoliet.domain.medewerker;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Naam {
    private String voornaam;
    private String initialen;

    private String achternaam;

    protected Naam() {
    }

    public Naam(String voornaam, String initialen, String achternaam) {
        this.voornaam = voornaam;
        this.initialen = initialen;
        this.achternaam = achternaam;
    }

    public Naam(String voornaam, String achternaam) {
        this(voornaam, "", achternaam);
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getInitialen() {
        return initialen;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setInitialen(String initialen) {
        this.initialen = initialen;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Naam naam = (Naam) o;
        return Objects.equals(voornaam, naam.voornaam) && Objects.equals(initialen, naam.initialen) && Objects.equals(achternaam, naam.achternaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voornaam, initialen, achternaam);
    }
}
