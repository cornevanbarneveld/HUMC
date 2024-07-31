package nl.hu.inno.humc.monoliet.domain.opdrachtgever;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.monoliet.domain.exception.InvalidTelException;

import java.util.Objects;

@Embeddable
public class TelefoonNummer {
    private String telefoonNummer;

    public TelefoonNummer(String telefoonNummer) {
        if (!isValid(telefoonNummer)) {
            throw new InvalidTelException();
        }
        this.telefoonNummer = telefoonNummer;
    }

    public TelefoonNummer() {

    }

    public boolean isValid( String telefoonNummer) {
        return (telefoonNummer.startsWith("+") && !telefoonNummer.matches("[a-zA-Z]+"));
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return telefoonNummer.equals(((TelefoonNummer) o).telefoonNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefoonNummer);
    }
}
