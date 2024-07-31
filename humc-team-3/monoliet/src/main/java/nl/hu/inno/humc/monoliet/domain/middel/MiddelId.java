package nl.hu.inno.humc.monoliet.domain.middel;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import nl.hu.inno.humc.monoliet.domain.exception.IncorrectIdException;

import java.io.Serializable;

@Embeddable
public class MiddelId implements Serializable {

    private String id;

    public MiddelId(String id) {
        if (isIdCorrect(id)) {
            this.id = id;
        } else {
            throw new IncorrectIdException();
        }
    }

    public MiddelId() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isIdCorrect(String id) {
        return id.length() > 0 && id.length() < 11;
    }
}
