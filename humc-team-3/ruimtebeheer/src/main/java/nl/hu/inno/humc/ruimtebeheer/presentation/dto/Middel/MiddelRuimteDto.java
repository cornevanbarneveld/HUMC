package nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel;


import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.TypeGevaar;

import java.time.LocalDateTime;

public record MiddelRuimteDto(String naam,
                              String beschrijving,
                              TypeGevaar typegevaar,
                              String middelId,
                              Long ruimteId,
                              LocalDateTime registratieDatum,
                              LocalDateTime houdbaarheidsDatum) {
}
