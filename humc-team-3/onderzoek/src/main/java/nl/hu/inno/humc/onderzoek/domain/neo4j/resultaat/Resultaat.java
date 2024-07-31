package nl.hu.inno.humc.onderzoek.domain.neo4j.resultaat;

import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Node
public class Resultaat {
    @GeneratedValue
    @Id
    private Long id;
    private ResultaatType type;
    private LocalDateTime datum;
    private String filename;
    private byte[] fileData;

    public Resultaat(ResultaatType type) {
        this.type = type;
        this.datum = LocalDateTime.now();
    }

    public Resultaat() {

    }
}
