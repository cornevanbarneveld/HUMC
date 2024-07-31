package nl.hu.inno.humc.ruimtebeheer.presentation.dto.Laboratorium;

public record LaboratoriumDto(
        Long ruimteId,
        String postcode,
        String straatnaam,
        int huisnummer,
        int ruimtenummer,
        int halnummer,
        String naam,
        String type,
        Long beheerderId) {
}
