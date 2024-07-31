package nl.hu.inno.humc.roostering.presentation.dto;


import java.time.LocalDateTime;

public record MiddelDto(
        String naam,
        String beschrijving,
        String typegevaar,
        Long middelId,
        LocalDateTime registratieDatum,
        LocalDateTime houdbaarheidsDatum


) {
}
