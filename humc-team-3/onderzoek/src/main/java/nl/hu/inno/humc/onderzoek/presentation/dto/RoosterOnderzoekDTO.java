package nl.hu.inno.humc.onderzoek.presentation.dto;

import java.time.LocalDateTime;

public record RoosterOnderzoekDTO(String ruimteNummer,
                                  LocalDateTime beginDatum,
                                  LocalDateTime eindDatum,
                                  String beschrijving,
                                  int onderzoekersId) { }