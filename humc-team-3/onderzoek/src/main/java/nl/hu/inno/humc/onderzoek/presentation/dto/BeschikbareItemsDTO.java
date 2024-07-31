package nl.hu.inno.humc.onderzoek.presentation.dto;

import java.util.Map;
import java.util.UUID;

public record BeschikbareItemsDTO (UUID uuid, Map<String, Integer> beschikbareMiddelen, Map<String, Integer> nietBeschikbareMiddelen, Long ruimteId, boolean succes) {
}
