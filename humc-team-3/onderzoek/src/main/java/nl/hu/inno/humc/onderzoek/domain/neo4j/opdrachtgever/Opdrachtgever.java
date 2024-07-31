package nl.hu.inno.humc.onderzoek.domain.neo4j.opdrachtgever;

import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Opdrachtgever {
    @GeneratedValue
    @Id
    private Long id;
    private String naam;
    private String bedrijf;
    private String email;
    private String telefoon;

    public Opdrachtgever() {
    }

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(String bedrijf) {
        this.bedrijf = bedrijf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }
}
