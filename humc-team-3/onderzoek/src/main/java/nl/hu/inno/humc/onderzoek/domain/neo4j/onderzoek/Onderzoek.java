package nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek;

import jakarta.persistence.*;
import nl.hu.inno.humc.onderzoek.domain.exception.InvalidDateException;
import nl.hu.inno.humc.onderzoek.domain.neo4j.opdrachtgever.Opdrachtgever;
import nl.hu.inno.humc.onderzoek.domain.neo4j.resultaat.Resultaat;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Node
public class Onderzoek {
    @GeneratedValue
    @Id
    private Long id;
    private OnderzoekBudget budget;
    private OnderzoekPeriode periode;
    @Relationship(type = "OPDRACHTGEVER", direction = Relationship.Direction.OUTGOING)
    private Opdrachtgever opdrachtgever;
    private String locatie;
    private String beschrijving;
    @Relationship(type = "RESULTAAT", direction = Relationship.Direction.OUTGOING)
    private List<Resultaat> resultaten = new ArrayList<>();
    @ElementCollection
    private List<MedewerkerId> onderzoekers = new ArrayList<>();
    private OnderzoekStatus onderzoekStatus = OnderzoekStatus.CONCEPT;
    private List<Middel> middelList = new ArrayList<>();

    public Onderzoek() {

    }

    public Onderzoek(Long id, OnderzoekBudget budget, OnderzoekPeriode periode, String beschrijving, List<MedewerkerId> onderzoekers, String onderzoekStatus, String locatie, Map<String, Integer> middelList) {
        this.id = id;
        this.budget = budget;
        this.periode = periode;
        this.beschrijving = beschrijving;
        this.onderzoekers = onderzoekers;
        this.locatie = locatie;

        List<Middel> middellen = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : middelList.entrySet()) {
            middellen.add(new Middel(entry.getKey(), entry.getValue()));
        }
        this.middelList = middellen;

        if (onderzoekStatus != null) {
            this.onderzoekStatus = OnderzoekStatus.valueOf(onderzoekStatus);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBudget(Double budget) {
        this.budget = new OnderzoekBudget(budget);
    }

    public Double getBudget() {
        if (budget == null) {
            return 0.0;
        }
        return budget.getBudget();
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public LocalDateTime getBegindatum() {
        if (periode == null) {
            return LocalDateTime.of(1900, 1, 1, 0, 0);
        }
        return periode.getBegindatum();
    }

    public LocalDateTime getEinddatum() {
        if (periode == null) {
            return LocalDateTime.of(1900, 1, 1, 0, 0);
        }
        return periode.getEinddatum();
    }

    public void setPeriode(LocalDateTime beginDatum, LocalDateTime eindDatum) {
        try {
            this.periode = new OnderzoekPeriode(beginDatum, eindDatum);
        } catch (InvalidDateException e) {
            System.out.println("Invalid date: " + beginDatum + " " + eindDatum);
        }

    }

    public Opdrachtgever getOpdrachtgever() {
        return opdrachtgever;
    }

    public void setOpdrachtgever(Opdrachtgever opdrachtgever) {
        this.opdrachtgever = opdrachtgever;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public List<Middel> getMiddelList() {
        return middelList;
    }

    public void setMiddelList(List<Middel> middelList) {
        this.middelList = middelList;
    }

    public OnderzoekStatus getOnderzoekStatus() {
        return onderzoekStatus;
    }

    public void setOnderzoekStatus(OnderzoekStatus onderzoekStatus) {
        this.onderzoekStatus = onderzoekStatus;
    }

    public boolean AddResearcher(Long medewerkerId) {
        return onderzoekers.add(new MedewerkerId(medewerkerId));
    }

    public List<Resultaat> getResultaten() {
        return resultaten;
    }

    public void setResultaten(List<Resultaat> resultaten) {
        this.resultaten = resultaten;
    }

    public List<MedewerkerId> getOnderzoekers() {
        return onderzoekers;
    }

    public void setOnderzoekers(List<MedewerkerId> onderzoekers) {
        this.onderzoekers = onderzoekers;
    }

    public boolean changeOnderzoekStatus (String state) {

        OnderzoekStatus status = OnderzoekStatus.valueOf(state);

        if (status == OnderzoekStatus.ACTIEF) {
            return onderzoekStarten();
        } else if (status == OnderzoekStatus.GESCHRAPT) {
            return onderzoekSchrappen();
        } else if (status == OnderzoekStatus.AFGEROND) {
            return onderzoekAfronden();
        } else {
            return false;
        }
    }

    private boolean onderzoekAfronden() {
        if (!resultaten.isEmpty() && onderzoekStatus == OnderzoekStatus.ACTIEF && LocalDateTime.now().isAfter(periode.getBegindatum())) {
            onderzoekStatus = OnderzoekStatus.AFGEROND;
            return true;
        }
        return false;
    }

    private boolean onderzoekSchrappen() {
        if (onderzoekStatus == OnderzoekStatus.CONCEPT) {
            onderzoekStatus = OnderzoekStatus.GESCHRAPT;
            return true;
    }
        return false;
    }

    private boolean onderzoekStarten() {
        if (budget == null || periode == null) {
            return false;
        }

        if (budget.getBudget() > 0 && !onderzoekers.isEmpty() &&
                periode.getBegindatum().isAfter(LocalDateTime.now()) && onderzoekStatus == OnderzoekStatus.CONCEPT &&
                !Objects.equals(beschrijving, "")) {
            onderzoekStatus = OnderzoekStatus.ACTIEF;
            return true;
        }

        return false;
    }
}
