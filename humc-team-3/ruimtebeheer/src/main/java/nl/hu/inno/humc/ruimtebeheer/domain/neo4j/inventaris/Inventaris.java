package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.inventaris;


import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node
public class Inventaris {

    @GeneratedValue
    @Id
    private Long id;

    @Relationship(type = "middelenInventaris", direction = Relationship.Direction.OUTGOING)
    private List<Middel> middelenInventaris = new ArrayList<>();

    private List<Long> overDeDatumMiddelen = new ArrayList<>();

    @Relationship(type = "teVerplaatsenMiddelen", direction = Relationship.Direction.OUTGOING)
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
        this.middelenInventaris.add(middel);
    }

    public List<Middel> getMiddelen() {
        return middelenInventaris;
    }

    public void deleteMiddel(Long middelId) {
        middelenInventaris.removeIf(middel -> middel.getId().equals(middelId));
        overDeDatumMiddelen.removeIf(id -> id.equals(middelId));
    }

    public void checkMiddelenOverDeDatum(){
        for (Middel middel: middelenInventaris) {
            if (!middel.getHoudbaarheidsperiode().isperiodeVerlopen()) {
                overDeDatumMiddelen.add(middel.getId());
            }
        }
    }

    public List<Long> getOverDeDatumMiddelen() {
        return overDeDatumMiddelen;
    }

    public List<MiddelVerplaatsNotification> getTeVerplaatsenMiddelen() {
        return teVerplaatsenMiddelen;
    }

    public void addTeVerplaatsenMiddel(MiddelVerplaatsNotification middelVerplaatsNotification) {
        teVerplaatsenMiddelen.add(middelVerplaatsNotification);
    }
}
