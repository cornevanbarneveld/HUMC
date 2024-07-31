package nl.hu.inno.humc.roostering.domain.medewerker;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AdresGegevens {
    private String woonplaats;
    private String adres;
    private String postcode;

    public AdresGegevens() {
    }

    public AdresGegevens(String woonplaats, String adres, String postcode) {
        this.woonplaats = woonplaats;
        this.adres = adres;
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresGegevens that = (AdresGegevens) o;
        return Objects.equals(woonplaats, that.woonplaats) && Objects.equals(adres, that.adres) && Objects.equals(postcode, that.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(woonplaats, adres, postcode);
    }
}
