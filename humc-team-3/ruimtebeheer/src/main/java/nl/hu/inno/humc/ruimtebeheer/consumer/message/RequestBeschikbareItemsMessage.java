package nl.hu.inno.humc.ruimtebeheer.consumer.message;

import java.time.LocalDateTime;
import java.util.Map;

public record RequestBeschikbareItemsMessage(
        String bericht,
        Map<String, Integer> benodigdeMiddelen,
        LocalDateTime startDatumOnderzoek,
        LocalDateTime eindDatumOnderzoek

) {
}
