package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel;


import java.time.LocalDateTime;

import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.TypeGevaar;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.Periode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Middel {
    private String naam;
    private String beschrijving;

    @Relationship(type = "HOUDBAARHEID", direction = Relationship.Direction.OUTGOING)
    private Periode houdbaarheidsperiode;

    private TypeGevaar typeGevaar;

    @GeneratedValue
    @Id
    private Long middelId;



    public Middel(String naam, String beschrijving, TypeGevaar typeGevaar, LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.typeGevaar = typeGevaar;
        this.houdbaarheidsperiode = new Periode(registratieDatum, houdbaarheidsDatum);
    }

    protected Middel() {

    }

    public void setId(Long id) {
        this.middelId = id;
    }

    public Long getId() {
        return middelId;
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
        return middelId.equals(middel.middelId);
    }

    @Override
    public int hashCode() {
        return middelId != null ? middelId.hashCode() : 0;
    }
}
