package nl.hu.inno.humc.roostering.domain.medewerker;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Telefoonnummer {
    @Column(name = "telefoonnummer")
    private String value;

    protected Telefoonnummer() {
    }

    public static final Pattern PHONEPATTERN = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");

    public Telefoonnummer(String value) {
        if (PHONEPATTERN.matcher(value).matches()){
            this.value = value;
        } else {
            throw new IllegalArgumentException(value + " is not a valid phone number");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefoonnummer that = (Telefoonnummer) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
