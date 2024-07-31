package nl.hu.inno.humc.monoliet.domain.resultaat;

import jakarta.persistence.*;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

import java.time.LocalDateTime;

@Entity
public class Resultaat {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ResultaatType type;
    private LocalDateTime datum;
    @ManyToOne
    private Medewerker medewerker;

    private String filename;
    @Lob
    private byte[] fileData;

    public Resultaat(ResultaatType type) {
        this.type = type;
        this.datum = LocalDateTime.now();
    }

    public Resultaat() {

    }
}
