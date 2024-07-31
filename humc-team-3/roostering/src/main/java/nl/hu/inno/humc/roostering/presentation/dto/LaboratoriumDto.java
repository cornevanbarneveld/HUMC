package nl.hu.inno.humc.roostering.presentation.dto;

public record LaboratoriumDto(
        String postcode,
        String straatnaam,
        int huisnummer,
        int ruimtenummer,
        int halnummer,
        String naam,
        String type,
        Long beheerderId) {
}
