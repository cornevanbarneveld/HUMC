package nl.hu.inno.humc.monoliet.presentation.dto.Middel;

import jakarta.annotation.Nullable;
import nl.hu.inno.humc.monoliet.domain.middel.TypeGevaar;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MiddelDto(
        String naam,
        String beschrijving,
        TypeGevaar typegevaar,
        String middelId,
        LocalDateTime registratieDatum,
        LocalDateTime houdbaarheidsDatum


) {
}
