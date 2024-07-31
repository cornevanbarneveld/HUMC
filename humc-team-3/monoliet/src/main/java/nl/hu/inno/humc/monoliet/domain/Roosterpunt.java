package nl.hu.inno.humc.monoliet.domain;


import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roosterpunt {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Rooster rooster;

    //Valueobject afspraakId
    @OneToOne
    private Medewerker organisator;
    private LocalDateTime begintijd;
    private LocalDateTime eindtijd;
    @ManyToMany
    private List<Medewerker> betrokkenen;
    @ManyToMany
    private List<Apparaat> apparaten;
    @ManyToMany
    private List<Laboratorium> locaties;

    public Roosterpunt(Rooster rooster, Medewerker organisator, LocalDateTime begintijd, LocalDateTime eindtijd) {
        this.rooster = rooster;
        this.organisator = organisator;
        this.begintijd = begintijd;
        this.eindtijd = eindtijd;
        this.betrokkenen = new ArrayList<>();
        this.apparaten = new ArrayList<>();
        this.locaties = new ArrayList<>();
    }

    public Roosterpunt() {
    }

    public Long getId() {
        return id;
    }

    public Rooster getRooster() {
        return rooster;
    }

    public Medewerker getOrganisator() {
        return organisator;
    }


    public LocalDateTime getBegintijd() {
        return begintijd;
    }

    public LocalDateTime getEindtijd() {
        return eindtijd;
    }

    public List<Medewerker> getBetrokkenen() {
        return betrokkenen;
    }

    public List<Apparaat> getApparaten() {
        return apparaten;
    }

    public List<Laboratorium> getLocaties() {
        return locaties;
    }

    public void addApparaat(Apparaat apparaat) {
        this.apparaten.add(apparaat);
    }

    public void addLaboratorium(Laboratorium laboratorium) {
        this.locaties.add(laboratorium);
    }

    public void addMedewerker(Medewerker medewerker) {
        this.betrokkenen.add(medewerker);
    }
}
