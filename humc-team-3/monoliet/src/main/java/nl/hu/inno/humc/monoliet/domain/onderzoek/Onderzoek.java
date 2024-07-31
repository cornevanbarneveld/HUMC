package nl.hu.inno.humc.monoliet.domain.onderzoek;

import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.exception.InvalidDateException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import nl.hu.inno.humc.monoliet.domain.opdrachtgever.Opdrachtgever;
import nl.hu.inno.humc.monoliet.domain.resultaat.Resultaat;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "onderzoek")
public class Onderzoek {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private OnderzoekBudget budget;
    @Embedded
    private OnderzoekPeriode periode;
    @ManyToOne
    private Opdrachtgever opdrachtgever;
    private String beschrijving;
    @OneToMany
    private List<Resultaat> resultaten = new ArrayList<>();
    @ManyToMany
    @Cascade(CascadeType.ALL)
    private List<Medewerker> onderzoekers = new ArrayList<>();

    private OnderzoekStatus onderzoekStatus = OnderzoekStatus.CONCEPT;

    public Onderzoek() {
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
            System.out.println("Invalid date");
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

    public OnderzoekStatus getOnderzoekStatus() {
        return onderzoekStatus;
    }

    public void setOnderzoekStatus(OnderzoekStatus onderzoekStatus) {
        this.onderzoekStatus = onderzoekStatus;
    }

    public boolean AddResearcher(Medewerker medewerker) {

        return onderzoekers.add(medewerker);
    }

    public List<Resultaat> getResultaten() {
        return resultaten;
    }

    public void setResultaten(List<Resultaat> resultaten) {
        this.resultaten = resultaten;
    }

    public List<Medewerker> getOnderzoekers() {
        return onderzoekers;
    }

    public void setOnderzoekers(List<Medewerker> onderzoekers) {
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
