package nl.hu.inno.humc.onderzoek.domain.postgress.resultaat;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Resultaat {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ResultaatType type;
    private LocalDateTime datum;
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
