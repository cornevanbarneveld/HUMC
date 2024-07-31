package nl.hu.inno.humc.roostering.presentation.dto;

import java.time.LocalDateTime;

public record TestDto (
    Long ruimteNummer,
    LocalDateTime beginDatum,
    LocalDateTime eindDatum,
    String beschrijving,
    Long onderzoekersId){
}
