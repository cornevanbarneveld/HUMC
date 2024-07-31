package nl.hu.inno.humc.monoliet.domain.opdrachtgever;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Email {
    private String email;

    public Email(String email) {
        if (email.contains("@") && email.length() >= 3)

        this.email = email;
    }

    public Email() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return email.equals(((Email) o).email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
