package nl.hu.inno.humc.roostering.producer.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class StartOnderzoekMessage {

    public Long ruimteNummer;
    public LocalDateTime beginDatum;
    public LocalDateTime eindDatum;
    public String beschrijving;
    public Long onderzoekersId;

    protected StartOnderzoekMessage() {
    }

    public StartOnderzoekMessage(Long ruimteNummer, LocalDateTime beginDatum, LocalDateTime eindDatum, String beschrijving, Long onderzoekersId) {
        this.ruimteNummer = ruimteNummer;
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;
        this.beschrijving = beschrijving;
        this.onderzoekersId = onderzoekersId;
    }
}
