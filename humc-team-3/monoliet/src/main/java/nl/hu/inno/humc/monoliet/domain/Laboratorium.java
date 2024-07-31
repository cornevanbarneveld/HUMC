package nl.hu.inno.humc.monoliet.domain;


import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.exception.BeheerderOnbevoegdException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Functie;
import nl.hu.inno.humc.monoliet.domain.ruimte.Ruimte;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

@Entity
public class Laboratorium extends Ruimte {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToOne
    private Medewerker beheerder;


    public Laboratorium(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type, Medewerker beheerder) {
        super(postcode,straatnaam, huisnummer, ruimtenummer, halnummer, naam);
        this.type = type;
        this.beheerder = beheerder;
    }

    public Laboratorium(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type) {
        super(postcode,straatnaam, huisnummer, ruimtenummer, halnummer, naam);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setBeheerder(Medewerker medewerker) {
        if (medewerker.getFunctie() != Functie.ARTS) {
            throw new BeheerderOnbevoegdException();
        }
        beheerder = medewerker;
    }

    public Medewerker getBeheerder() {
        return beheerder;
    }
    public Laboratorium() {}

}
