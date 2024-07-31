package nl.hu.inno.humc.monoliet.presentation.dto;


import java.time.LocalDateTime;
import java.util.List;

public record SimpleRoosterpuntDto(Long id, Long roosterId, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd,
                                   List<Long> betrokkenMedewerkers, List<Long> apparaten, List<Long> locaties
) {
}
