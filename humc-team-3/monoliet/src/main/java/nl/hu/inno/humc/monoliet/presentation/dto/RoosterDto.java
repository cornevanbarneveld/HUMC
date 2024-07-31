package nl.hu.inno.humc.monoliet.presentation.dto;

import nl.hu.inno.humc.monoliet.domain.Roosterpunt;

import java.util.List;

public record RoosterDto(Long id, List<Long> afspraaknummers) {
}
