package nl.hu.inno.humc.roostering.presentation.dto;

import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;

import java.util.List;

public record RoosterDto(String id, List<RoosterpuntId> afspraaknummers) {
}
