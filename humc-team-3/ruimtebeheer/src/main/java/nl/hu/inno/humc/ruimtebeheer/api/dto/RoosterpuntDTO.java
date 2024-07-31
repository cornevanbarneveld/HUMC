package nl.hu.inno.humc.ruimtebeheer.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RoosterpuntDTO(String beschrijving, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd,
                             List<Long> middelIds, Long ruimteNummer) {
}
