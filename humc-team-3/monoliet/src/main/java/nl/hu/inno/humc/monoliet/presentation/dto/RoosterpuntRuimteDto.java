package nl.hu.inno.humc.monoliet.presentation.dto;


import java.time.LocalDateTime;

public record RoosterpuntRuimteDto(LocalDateTime beginDatum, LocalDateTime eindDatum, Long ruimteId) {
}
