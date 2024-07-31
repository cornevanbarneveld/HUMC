package nl.hu.inno.humc.monoliet.domain;

import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.domain.middel.MiddelId;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Inventaris {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Apparaat> apparaten = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Middel> middelen = new ArrayList<>();

    @GeneratedValue
    @Id
    private Long id;

    @ElementCollection
    private List<MiddelId> overDeDatumMiddelen = new ArrayList<>();

    public Inventaris() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void addApparaat(Apparaat apparaat) {
        this.apparaten.add(apparaat);
    }

    public void addMiddel(Middel middel) {
        this.middelen.add(middel);
    }

    public List<Apparaat> getApparaten() {
        return apparaten;
    }

    public List<Middel> getMiddelen() {
        return middelen;
    }

    public void deleteMiddel(String middelId) {
        middelen.removeIf(middel -> middel.getId().getId().equals(middelId));
        overDeDatumMiddelen.removeIf(middel -> middel.getId().equals(middelId));
    }

    public void deleteApparat(String apparaatId) {
        apparaten.removeIf(apparaat -> apparaat.getApparaatId().getId().equals(apparaatId));
    }

    public void checkMiddelenOverDeDatum(){
        for (Middel middel: middelen) {
            if (!middel.getHoudbaarheidsperiode().isproductGeldig()) {
                overDeDatumMiddelen.add(middel.getId());
            }
        }
    }

    public List<MiddelId> getOverDeDatumMiddelen() {
        return overDeDatumMiddelen;
    }
}
