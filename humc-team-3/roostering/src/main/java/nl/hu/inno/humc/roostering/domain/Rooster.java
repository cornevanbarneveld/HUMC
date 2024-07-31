package nl.hu.inno.humc.roostering.domain;

import jakarta.persistence.*;
import nl.hu.inno.humc.roostering.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.roostering.domain.exception.RoosterpuntNotFoundException;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.domain.roosterpunt.Roosterpunt;
import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "rooster")
public class Rooster implements Serializable {
//    @Id
//    @GeneratedValue
//    @Column(name = "RoosterId")
//    private Long id;
    @Id
    @Column(name = "RoosterId")
    private String id = UUID.randomUUID().toString();

    @OneToMany
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<Roosterpunt> afspraken;

    public Rooster() {
        this.afspraken = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Roosterpunt> getAfspraken() {
        return afspraken;
    }

    public List<Roosterpunt> getAfsprakenVandaag(){
        List<Roosterpunt> RoosterPuntenVandaag = new ArrayList<>();
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getBegintijd().getDayOfYear() == LocalDateTime.now().getDayOfYear() ||
                roosterpunt.getEindtijd().getDayOfYear() == LocalDateTime.now().getDayOfYear()){
                RoosterPuntenVandaag.add(roosterpunt);
            }
        });
        return RoosterPuntenVandaag;
    }

    public Roosterpunt addRoosterpunt(RoosterpuntId roosterpuntId, String beschrijving, Long ruimteNummer) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenOrganisator = this.getAfsprakenByMedewerker(roosterpuntId.getOrganisator());
        List<Roosterpunt> roosterpuntenVanDeRuimte = this.getAfsprakenByRuimte(ruimteNummer);

        if (roosterpuntId.getBegintijd().isBefore(LocalDateTime.now()) ||
                roosterpuntId.getEindtijd().isBefore(LocalDateTime.now()) ||
                roosterpuntId.getBegintijd().isAfter(roosterpuntId.getEindtijd())){
            throw new DateTimeException("Begintijd en/of eindtijd is/zijn niet goed ingevuld!");
        }

        if (this.ObjectIsBezet(roosterpuntenOrganisator, roosterpuntId.getBegintijd(), roosterpuntId.getEindtijd())){
            throw new NietBeschikbaarException("Deze medewerker heeft al een roosterpunt in het rooster staan op dat moment!");
        }

        if (this.ObjectIsBezet(roosterpuntenVanDeRuimte, roosterpuntId.getBegintijd(), roosterpuntId.getEindtijd())){
            throw new NietBeschikbaarException("De ruimte die u probeert te reserveren is al in gebruik op dat moment!");
        }
        Roosterpunt afspraak = new Roosterpunt(roosterpuntId, beschrijving, ruimteNummer);
        afspraken.add(afspraak);
        return afspraak;
    }

    public String annuleerRoosterpunt(Roosterpunt roosterpunt) {
        afspraken.remove(roosterpunt);
        return "Roosterpunt geannuleerd!";
    }

    public Roosterpunt getAfspraakByRoosterpuntIdSamenstelling(RoosterpuntId roosterpuntId) {
        for (Roosterpunt roosterpunt : this.afspraken) {
            if (Objects.equals(roosterpunt.getRoosterpuntId(), roosterpuntId)) {
                return roosterpunt;
            }
        }
        throw new RoosterpuntNotFoundException();
    }

    public void addMiddelToRoosterpunt(Long middelId, Roosterpunt roosterpunt) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenVanHetMiddel = this.getAfsprakenByMiddel(middelId);
        if (this.overlapMetAnderRoosterpunt(roosterpuntenVanHetMiddel, roosterpunt)){
            throw new NietBeschikbaarException("Het middel dat u probeert te reserveren is al in gebruik op dat moment!");
        }
        if (roosterpunt.getMiddelen().contains(middelId)){
            throw new NietBeschikbaarException("Middel is al toegevoegd aan dit roosterpunt.");
        }
        roosterpunt.addMiddel(middelId);
    }

    public void setRoosterpuntRuimte(Long ruimteNummer, Roosterpunt roosterpunt) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenVanDeRuimte = this.getAfsprakenByRuimte(ruimteNummer);
        if (this.overlapMetAnderRoosterpunt(roosterpuntenVanDeRuimte, roosterpunt)){
            throw new NietBeschikbaarException("De ruimte die u probeert te reserveren is al in gebruik op dat moment!");
        }
        roosterpunt.setRuimteNummer(ruimteNummer);
    }

    public void addBetrokkeneToRoosterpunt(Medewerker medewerker, Roosterpunt roosterpunt) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenVanDeMedewerker = this.getAfsprakenByMedewerker(medewerker);
        if (this.overlapMetAnderRoosterpunt(roosterpuntenVanDeMedewerker, roosterpunt)) {
            throw new NietBeschikbaarException("De medewerker die u probeert toe te voegen heeft al een roosterpunt op dat moment!");
        }
        if (roosterpunt.getBetrokkenen().contains(medewerker)) {
            throw new NietBeschikbaarException("Medewerker is al betrokken in dit roosterpunt.");
        }
        roosterpunt.addMedewerker(medewerker);
    }

    public List<Roosterpunt> getAfsprakenVanDeRuimte(Long ruimteNummer) {
        List<Roosterpunt> Roosterpunten = new ArrayList<>();
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getRuimteNummer().equals(ruimteNummer)){
                Roosterpunten.add(roosterpunt);
            }
        });
        return Roosterpunten;
    }

    public void verwijderMiddelIdUitAlleAfspraken(Long middelId) {
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getMiddelen().contains(middelId)){
                roosterpunt.removeMiddle(middelId);
            }
        });
    }

    private boolean ObjectIsBezet(List<Roosterpunt> roosterpunten, LocalDateTime begintijd, LocalDateTime eindtijd) {
        for (Roosterpunt roosterpunt: roosterpunten){
            if (roosterpunt.getBegintijd().isBefore(eindtijd) && begintijd.isBefore(roosterpunt.getEindtijd())) {
                return true;
            }
        }
        return false;
    }

    private List<Roosterpunt> getAfsprakenByMiddel(Long middelId) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getMiddelen().contains(middelId)){
                roosterpunten.add(roosterpunt);
            }
        });
        return roosterpunten;
    }

    private List<Roosterpunt> getAfsprakenByRuimte(Long ruimteNummer) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getRuimteNummer().equals(ruimteNummer)){
                roosterpunten.add(roosterpunt);
            }
        });
        return roosterpunten;
    }

    private List<Roosterpunt> getAfsprakenByMedewerker(Medewerker medewerker) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        afspraken.forEach(roosterpunt -> {
            if (roosterpunt.getBetrokkenen().contains(medewerker) || roosterpunt.getOrganisator().equals(medewerker)){
                roosterpunten.add(roosterpunt);
            }
        });
        return roosterpunten;
    }

    private boolean overlapMetAnderRoosterpunt(List<Roosterpunt> roosterpunten, Roosterpunt ditRoosterpunt) {
        for (Roosterpunt roosterpunt : roosterpunten) {
            if (roosterpunt.getBegintijd().isBefore(ditRoosterpunt.getEindtijd()) &&
                    ditRoosterpunt.getBegintijd().isBefore(roosterpunt.getEindtijd()) &&
                    !roosterpunt.equals(ditRoosterpunt)) {
                return true;
            }
        }
        return false;
    }
}
