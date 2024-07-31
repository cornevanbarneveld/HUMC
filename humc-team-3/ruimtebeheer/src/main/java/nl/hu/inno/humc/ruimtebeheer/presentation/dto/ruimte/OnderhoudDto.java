package nl.hu.inno.humc.ruimtebeheer.presentation.dto.ruimte;

import java.time.LocalDateTime;

public record OnderhoudDto(LocalDateTime startDatum, LocalDateTime eindDatum, Long ruimteId) {
}
