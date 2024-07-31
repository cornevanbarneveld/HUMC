package nl.hu.inno.humc.roostering.domain.roosterpunt;


import jakarta.persistence.*;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roosterpunt {
    @Id
    private RoosterpuntId roosterpuntId;
    private String beschrijving;
    @ManyToMany
    private List<Medewerker> betrokkenen;
    @ElementCollection
    private List<Long> middelIds;
    private Long ruimteNummer;

    public Roosterpunt(RoosterpuntId roosterpuntId, String beschrijving, Long ruimteNummer) {
        this.roosterpuntId = roosterpuntId;
        this.beschrijving = beschrijving;
        this.betrokkenen = new ArrayList<>();
        this.middelIds = new ArrayList<>();
        this.ruimteNummer = ruimteNummer;
    }

    public Roosterpunt() {
    }
    public RoosterpuntId getRoosterpuntId() {
        return roosterpuntId;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public Medewerker getOrganisator() {
        return roosterpuntId.getOrganisator();
    }


    public LocalDateTime getBegintijd() {
        return roosterpuntId.getBegintijd();
    }

    public LocalDateTime getEindtijd() {
        return roosterpuntId.getEindtijd();
    }

    public List<Medewerker> getBetrokkenen() {
        return betrokkenen;
    }

    public List<Long> getMiddelen() {
        return middelIds;
    }

    public Long getRuimteNummer() {
        return ruimteNummer;
    }

    public void addMiddel(Long middelId) {
        this.middelIds.add(middelId);
    }

    public void setRuimteNummer(Long ruimteNummer) {
        this.ruimteNummer = ruimteNummer;
    }

    public void addMedewerker(Medewerker medewerker) {
        this.betrokkenen.add(medewerker);
    }

    public void removeMiddle(Long middelId) {
        this.middelIds.remove(middelId);
    }
}
