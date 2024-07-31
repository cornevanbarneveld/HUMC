package nl.hu.inno.humc.monoliet.domain.apparaat;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Apparaat {

    @EmbeddedId
    private ApparaatId id;
    private String naam;
    private boolean elektrisch;

    public Apparaat(String naam, boolean elektrisch, String id) {
        this.naam = naam;
        this.elektrisch = elektrisch;
        this.id = new ApparaatId(id);
    }

    public Apparaat() {}

    public String getNaam() {
        return naam;
    }

    public boolean isElektrisch() {
        return elektrisch;
    }

    public void setId(ApparaatId id) {
        this.id = id;
    }


    public ApparaatId getApparaatId() {
        return id;
    }


    public boolean isBeschikbaar(Timestamp begintijd, Timestamp eindtijd) {
        return true; // this.rooster.heeftNogGeenAfspraken(date, begintijd, eindtijd);
        // Verder nog implementatie wanneer Rooster erin staat met checken van date en time.
    }
}
