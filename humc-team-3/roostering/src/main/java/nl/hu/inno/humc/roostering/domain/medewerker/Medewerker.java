package nl.hu.inno.humc.roostering.domain.medewerker;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Medewerker {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Naam naam;
    @Embedded
    private AdresGegevens adresGegevens;
    @Embedded
    private Email email;
    @Embedded
    private Telefoonnummer telefoonnummer;
    @Enumerated(EnumType.STRING)
    private Functie functie;

    public Medewerker(Naam naam, AdresGegevens adresGegevens, Email email, Telefoonnummer telefoonnummer, String functie) {
        this.naam = naam;
        this.adresGegevens = adresGegevens;
        this.email = email;
        this.telefoonnummer = telefoonnummer;

        if (!Objects.equals(functie, "arts") && !Objects.equals(functie, "assistent") &&
                !Objects.equals(functie, "schoonmaker") && !Objects.equals(functie, "secretaresse")){
            throw new IllegalArgumentException(functie + " is not a valid medewerkers function");
        }
        this.functie = Functie.valueOf(functie.toUpperCase());
    }



    public Medewerker() {
    }

    public Long getId() {
        return id;
    }

    public void setMedewerkerId(Long id) {
        this.id = id;
    }

    public Naam getNaam() {
        return naam;
    }

    public AdresGegevens getAdresGegevens() {
        return adresGegevens;
    }

    public String getWoonplaats() {
        return adresGegevens.getWoonplaats();
    }

    public String getAdres() {
        return adresGegevens.getAdres();
    }

    public String getPostcode() {
        return adresGegevens.getPostcode();
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getTelefoonnummer() {
        return telefoonnummer.getValue();
    }

    public Functie getFunctie() {
        return functie;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setTelefoonnummer(Telefoonnummer telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public void setNaam(Naam naam) {
        this.naam = naam;
    }

    public void setAdresGegevens(AdresGegevens adresGegevens) {
        this.adresGegevens = adresGegevens;
    }

    public void setFunctie(Functie functie) {
        this.functie = functie;
    }
}
