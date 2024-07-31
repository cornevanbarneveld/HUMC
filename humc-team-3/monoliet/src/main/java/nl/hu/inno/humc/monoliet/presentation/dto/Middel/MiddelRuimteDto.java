package nl.hu.inno.humc.monoliet.presentation.dto.Middel;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.hu.inno.humc.monoliet.domain.middel.TypeGevaar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MiddelRuimteDto(String naam,
                              String beschrijving,
                              TypeGevaar typegevaar,
                              String middelId,
                              Long ruimteId,
                              LocalDateTime registratieDatum,
                              LocalDateTime houdbaarheidsDatum) {
}
