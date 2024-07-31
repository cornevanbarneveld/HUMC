package nl.hu.inno.humc.monoliet.presentation.dto;

import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RoosterpuntDTO(Long id, Long roosterId, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd,
                             List<Medewerker> betrokkenen, List<Apparaat> apparaten, List<Laboratorium> locaties) {


}
