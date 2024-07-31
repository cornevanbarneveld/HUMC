package nl.hu.inno.humc.roostering.presentation.dto;

import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;

import java.time.LocalDateTime;
import java.util.List;

public record RoosterpuntDto(RoosterpuntId roosterpuntId, String beschrijving, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd,
                             List<Medewerker> betrokkenen, List<Long> middelIds, Long ruimteNummer) {


}
