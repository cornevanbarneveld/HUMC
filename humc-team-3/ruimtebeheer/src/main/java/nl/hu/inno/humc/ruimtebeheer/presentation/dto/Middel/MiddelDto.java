package nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel;


import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.TypeGevaar;

import java.time.LocalDateTime;

public record MiddelDto(
        String naam,
        String beschrijving,
        TypeGevaar typegevaar,
        Long middelId,
        LocalDateTime registratieDatum,
        LocalDateTime houdbaarheidsDatum


) {
}
