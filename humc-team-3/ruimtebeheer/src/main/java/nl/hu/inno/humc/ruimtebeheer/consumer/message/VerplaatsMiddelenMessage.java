package nl.hu.inno.humc.ruimtebeheer.consumer.message;

import java.time.LocalDateTime;

public record VerplaatsMiddelenMessage(
        Long ruimteId,
        Long middelId,
        LocalDateTime beginDatum,
        LocalDateTime eindDatum
) {
}
