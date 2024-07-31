package nl.hu.inno.humc.onderzoek.presentation.dto;

import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.MedewerkerId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record OnderzoekDTO(Long id, Double budget, LocalDateTime beginDatum, LocalDateTime eindDatum,
                           String beschrijving, List<MedewerkerId> onderzoekers, String locatie,
                           String status, String bericht, Map<String, Integer> middelList) { }