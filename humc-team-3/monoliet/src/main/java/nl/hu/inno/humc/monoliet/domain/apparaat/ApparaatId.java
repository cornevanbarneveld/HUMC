package nl.hu.inno.humc.monoliet.domain.apparaat;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.monoliet.domain.exception.IncorrectIdException;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApparaatId implements Serializable {

    private String id;

    public ApparaatId(String id) {
        if (isIdCorrect(id)) {
            this.id = id;
        } else {
            throw new IncorrectIdException();
        }
    }

    protected ApparaatId() {

    }

    public boolean isIdCorrect(String id) {
        return id.length() > 0 && id.length() < 11;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApparaatId apparaatId = (ApparaatId) o;
        return Objects.equals(id, apparaatId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
