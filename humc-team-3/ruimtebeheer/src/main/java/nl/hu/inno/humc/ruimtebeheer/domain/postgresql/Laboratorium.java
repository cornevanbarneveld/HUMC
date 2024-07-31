package nl.hu.inno.humc.ruimtebeheer.domain.postgresql;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte.Ruimte;

@Entity
public class Laboratorium extends Ruimte {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Long beheerderId;


    public Laboratorium(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type, Long beheerderId) {
        super(postcode,straatnaam, huisnummer, ruimtenummer, halnummer, naam);
        this.type = type;
        this.beheerderId = beheerderId;
    }

    public Laboratorium(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type) {
        super(postcode,straatnaam, huisnummer, ruimtenummer, halnummer, naam);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setBeheerder(Long beheerderId) {
        this.beheerderId = beheerderId;
    }

    public Long getBeheerder() {
        return beheerderId;
    }
    public Laboratorium() {}

}
