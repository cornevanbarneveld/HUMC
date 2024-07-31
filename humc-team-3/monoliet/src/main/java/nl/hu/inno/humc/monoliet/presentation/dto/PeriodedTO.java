package nl.hu.inno.humc.monoliet.presentation.dto;

import java.time.LocalDateTime;

public record PeriodedTO(LocalDateTime beginDatum, LocalDateTime eindDatum) {
}
