package nl.hu.inno.humc.monoliet.presentation.dto;

import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

import java.time.LocalDateTime;
import java.util.List;

public record OnderzoekDTO(Long id, Double budget, LocalDateTime beginDatum, LocalDateTime eindDatum,
                           String beschrijving, List<Medewerker> onderzoekers, String status) {
}