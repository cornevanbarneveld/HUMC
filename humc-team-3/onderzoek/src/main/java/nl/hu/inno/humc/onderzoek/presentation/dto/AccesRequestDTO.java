package nl.hu.inno.humc.onderzoek.presentation.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

public record AccesRequestDTO (String bericht, Map<String, Integer> benodigdeMiddelen, LocalDateTime startDatumOnderzoek, LocalDateTime eindDatumOnderzoek) {

}
