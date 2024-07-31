package nl.hu.inno.humc.onderzoek.domain.postgress.onderzoek;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.onderzoek.domain.exception.IncorrectIdException;

import java.io.Serializable;

@Embeddable
public class MedewerkerId implements Serializable {

    private Long id;

    public MedewerkerId(Long id) {
        if (isIdCorrect(id)) {
            this.id = id;
        } else {
            throw new IncorrectIdException();
        }
    }

    protected MedewerkerId() {

    }

    public boolean isIdCorrect(Long id) {
        return id.toString().length() > 0 && id.toString().length() < 11;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
