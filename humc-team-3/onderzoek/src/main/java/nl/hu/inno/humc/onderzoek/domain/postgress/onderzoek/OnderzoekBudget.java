package nl.hu.inno.humc.onderzoek.domain.postgress.onderzoek;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.onderzoek.domain.exception.InvalidBudgetException;

@Embeddable
public class OnderzoekBudget {
    private Double budget;

    public OnderzoekBudget(Double budget) {
        if ( budget < 0) {
            throw new InvalidBudgetException();
        }
        this.budget = budget;
    }

    public OnderzoekBudget() {

    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
