package nl.hu.inno.humc.roostering.domain.roosterpunt;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RoosterpuntId implements Serializable {
    @OneToOne
    private Medewerker organisator;
    private LocalDateTime begintijd;
    private LocalDateTime eindtijd;

    public RoosterpuntId(Medewerker organisator, LocalDateTime begintijd, LocalDateTime eindtijd) {
        this.organisator = organisator;
        this.begintijd = begintijd;
        this.eindtijd = eindtijd;
    }

    protected RoosterpuntId() {}

    public Medewerker getOrganisator() {
        return organisator;
    }

    public LocalDateTime getBegintijd() {
        return begintijd;
    }

    public LocalDateTime getEindtijd() {
        return eindtijd;
    }

    public void setOrganisator(Medewerker organisator) {
        this.organisator = organisator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoosterpuntId that = (RoosterpuntId) o;
        return Objects.equals(organisator, that.organisator) && Objects.equals(begintijd, that.begintijd) && Objects.equals(eindtijd, that.eindtijd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisator, begintijd, eindtijd);
    }
}
