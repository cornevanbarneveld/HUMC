package nl.hu.inno.humc.ruimtebeheer.domain.neo4j;


import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.locatie.Locatie;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Laboratorium extends Ruimte {
    private String type;
    private Long beheerderId;

    public Laboratorium(String postcode, String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type) {
        super(postcode, straatnaam, huisnummer, ruimtenummer, halnummer, naam);
        this.type = type;
    }

    public Laboratorium(String type, String naam) {
        super();
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
