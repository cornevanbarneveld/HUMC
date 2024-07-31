package nl.hu.inno.humc.ruimtebeheer.presentation.dto;

import java.time.LocalDateTime;

public record PeriodeDto(LocalDateTime beginDatum, LocalDateTime eindDatum) {
}
