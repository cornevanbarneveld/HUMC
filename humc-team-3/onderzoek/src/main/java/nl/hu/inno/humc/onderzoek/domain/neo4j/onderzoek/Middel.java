package nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public class Middel {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private int hoeveelheid;

    public Middel(String name, int hoeveelheid) {
        this.name = name;
        this.hoeveelheid = hoeveelheid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }
}
