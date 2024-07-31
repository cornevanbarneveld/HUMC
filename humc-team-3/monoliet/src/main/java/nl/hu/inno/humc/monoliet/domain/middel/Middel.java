package nl.hu.inno.humc.monoliet.domain.middel;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Middel {
    private String naam;
    private String beschrijving;

    @Embedded
    private Houdbaarheidsperiode houdbaarheidsperiode;

    @Enumerated(EnumType.STRING)
    private TypeGevaar typeGevaar;
    @EmbeddedId
    private MiddelId id;

    public Middel(String naam, String beschrijving, TypeGevaar typeGevaar, String id, LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.typeGevaar = typeGevaar;
        this.id = new MiddelId(id);
        this.houdbaarheidsperiode = new Houdbaarheidsperiode(registratieDatum, houdbaarheidsDatum);
    }

    public Middel() {

    }

    public void setId(MiddelId id) {
        this.id = id;
    }

    public MiddelId getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public TypeGevaar getTypeGevaar() {
        return typeGevaar;
    }

    public Houdbaarheidsperiode getHoudbaarheidsperiode() {
        return houdbaarheidsperiode;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setHoudbaarheidsperiode(Houdbaarheidsperiode houdbaarheidsperiode) {
        this.houdbaarheidsperiode = houdbaarheidsperiode;
    }

    public void setTypeGevaar(TypeGevaar typeGevaar) {
        this.typeGevaar = typeGevaar;
    }
}
