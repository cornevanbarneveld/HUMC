package nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek;

import nl.hu.inno.humc.onderzoek.domain.exception.InvalidBudgetException;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;


public class OnderzoekBudget {
    @GeneratedValue
    @Id
    private Long id;
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
