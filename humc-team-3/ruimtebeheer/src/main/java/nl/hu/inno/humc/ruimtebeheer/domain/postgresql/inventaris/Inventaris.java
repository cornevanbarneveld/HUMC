package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.inventaris;

import jakarta.persistence.*;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.middel.Middel;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Inventaris {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Middel> middelen = new ArrayList<>();

    @GeneratedValue
    @Id
    private Long id;

    @ElementCollection
    private List<Long> overDeDatumMiddelen = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<MiddelVerplaatsNotification> teVerplaatsenMiddelen = new ArrayList<>();

    public Inventaris() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void addMiddel(Middel middel) {
        this.middelen.add(middel);
    }

    public List<Middel> getMiddelen() {
        return middelen;
    }

    public void deleteMiddel(Long middelId) {
        middelen.removeIf(middel -> middel.getId().equals(middelId));
        overDeDatumMiddelen.removeIf(middelIdObject -> middelIdObject.equals(middelId));
    }

    public void checkMiddelenOverDeDatum(){
        overDeDatumMiddelen.clear();
        for (Middel middel: middelen) {
            if (!middel.getHoudbaarheidsperiode().isperiodeVerlopen()) {
                overDeDatumMiddelen.add(middel.getId());
            }
        }
    }

    public List<Long> getOverDeDatumMiddelen() {
        return overDeDatumMiddelen;
    }

    public void addTeVerplaatsenMiddel(MiddelVerplaatsNotification middelVerplaatsNotification) {
        this.teVerplaatsenMiddelen.add(middelVerplaatsNotification);
    }

    public void setMiddelen(List<Middel> middelen) {
        this.middelen = middelen;
    }

    public void setOverDeDatumMiddelen(List<Long> overDeDatumMiddelen) {
        this.overDeDatumMiddelen = overDeDatumMiddelen;
    }

    public List<MiddelVerplaatsNotification> getTeVerplaatsenMiddelen() {
        return teVerplaatsenMiddelen;
    }

    public void setTeVerplaatsenMiddelen(List<MiddelVerplaatsNotification> teVerplaatsenMiddelen) {
        this.teVerplaatsenMiddelen = teVerplaatsenMiddelen;
    }
}
