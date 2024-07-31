package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.middel;

import jakarta.persistence.*;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.Periode;

import java.time.LocalDateTime;

@Entity
public class Middel {
    private String naam;
    private String beschrijving;

    @Embedded
    private Periode houdbaarheidsperiode;

    @Enumerated(EnumType.STRING)
    private TypeGevaar typeGevaar;

    @GeneratedValue
    @Id
    private Long id;

    public Middel(String naam, String beschrijving, TypeGevaar typeGevaar, LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.typeGevaar = typeGevaar;
        this.houdbaarheidsperiode = new Periode(registratieDatum, houdbaarheidsDatum);
    }

    protected Middel() {

    }

    public Long getId() {
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

    public Periode getHoudbaarheidsperiode() {
        return houdbaarheidsperiode;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setHoudbaarheidsperiode(Periode houdbaarheidsperiode) {
        this.houdbaarheidsperiode = houdbaarheidsperiode;
    }

    public void setTypeGevaar(TypeGevaar typeGevaar) {
        this.typeGevaar = typeGevaar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Middel middel = (Middel) o;
        return id.equals(middel.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
