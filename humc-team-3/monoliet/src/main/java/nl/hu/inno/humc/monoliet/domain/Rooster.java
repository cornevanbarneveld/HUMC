package nl.hu.inno.humc.monoliet.domain;

import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.monoliet.domain.exception.RoosterpuntNotFoundException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "rooster")
public class Rooster implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "RoosterId")
    private Long id;

    @OneToMany
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Roosterpunt> afspraken;

    public Rooster() {
        this.afspraken = new ArrayList<>();
    }

    public Long getId() {
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

    public Roosterpunt addRoosterpunt(Medewerker organisator, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpunten = this.getAfsprakenByMedewerker(organisator);

        if (begintijd.isBefore(LocalDateTime.now()) || eindtijd.isBefore(LocalDateTime.now()) || begintijd.isAfter(eindtijd)){
            throw new DateTimeException("Begintijd en/of eindtijd is/zijn niet goed ingevuld!");
        }

        if (this.OrganisatorIsBezet(roosterpunten, begintijd, eindtijd)){
            throw new NietBeschikbaarException("Deze medewerker heeft al een roosterpunt in het rooster staan op dat moment!");
        }
        Roosterpunt afspraak = new Roosterpunt(this, organisator, begintijd, eindtijd);
        afspraken.add(afspraak);
        return afspraak;
    }

    public Roosterpunt getAfspraakById(Long id) {
        for (Roosterpunt roosterpunt : this.afspraken) {
            if (Objects.equals(roosterpunt.getId(), id)) {
                return roosterpunt;
            }
        }
        throw new RoosterpuntNotFoundException();
    }

    public void addApparaatToRoosterpunt(Apparaat apparaat, Roosterpunt roosterpunt) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenVanHetApparaat = this.getAfsprakenByApparaat(apparaat);
        if (this.overlapMetAnderRoosterpunt(roosterpuntenVanHetApparaat, roosterpunt)){
            throw new NietBeschikbaarException("Het apparaat dat u probeert te reserveren is al in gebruik op dat moment!");
        }
        if (roosterpunt.getApparaten().contains(apparaat)){
            throw new NietBeschikbaarException("Apparaat is al toegevoegd aan dit roosterpunt.");
        }
        roosterpunt.addApparaat(apparaat);
    }

    public void addLocatieToRoosterpunt(Laboratorium laboratorium, Roosterpunt roosterpunt) throws NietBeschikbaarException {
        List<Roosterpunt> roosterpuntenVanDeLocatie = this.getAfsprakenByLocatie(laboratorium);
        if (this.overlapMetAnderRoosterpunt(roosterpuntenVanDeLocatie, roosterpunt)){
            throw new NietBeschikbaarException("De locatie die u probeert te reserveren is al in gebruik op dat moment!");
        }
        if (roosterpunt.getLocaties().contains(laboratorium)){
            throw new NietBeschikbaarException("Locatie is al toegevoegd als locatie aan dit roosterpunt.");
        }
        roosterpunt.addLaboratorium(laboratorium);
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

    private boolean OrganisatorIsBezet(List<Roosterpunt> roosterpunten, LocalDateTime begintijd, LocalDateTime eindtijd) {
        for (Roosterpunt roosterpunt: roosterpunten){
            if (roosterpunt.getBegintijd().isBefore(eindtijd) && begintijd.isBefore(roosterpunt.getEindtijd())) {
                return true;
            }
        }
        return false;
    }

    private List<Roosterpunt> getAfsprakenByApparaat(Apparaat apparaat) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        for (Roosterpunt roosterpunt: this.afspraken){
            if (roosterpunt.getApparaten().contains(apparaat)){
                roosterpunten.add(roosterpunt);
            }
        }
        return roosterpunten;
    }

    private List<Roosterpunt> getAfsprakenByLocatie(Laboratorium laboratorium) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        for (Roosterpunt roosterpunt: this.afspraken){
            if (roosterpunt.getLocaties().contains(laboratorium)){
                roosterpunten.add(roosterpunt);
            }
        }
        return roosterpunten;
    }

    private List<Roosterpunt> getAfsprakenByMedewerker(Medewerker medewerker) {
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        for (Roosterpunt roosterpunt: this.afspraken){
            if (roosterpunt.getBetrokkenen().contains(medewerker) || roosterpunt.getOrganisator().equals(medewerker)){
                roosterpunten.add(roosterpunt);
            }
        }
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
