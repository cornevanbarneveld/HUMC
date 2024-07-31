package nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel;

import java.time.LocalDateTime;

public record MiddelVerplaatsNotificationDto (
        Long middelId,
        Long ruimteIdTo,
        LocalDateTime beginDatum,
        LocalDateTime eindDatum
){
}
