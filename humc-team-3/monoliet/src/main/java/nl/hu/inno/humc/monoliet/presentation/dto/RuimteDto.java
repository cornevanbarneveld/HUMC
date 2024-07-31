package nl.hu.inno.humc.monoliet.presentation.dto;

public record RuimteDto(
        Long id,
        String postcode,
        String straatnaam,
        int huisnummer,
        int ruimtenummer,
        int halnummer,
        String naam,
        InventarisDto inventarisDto
        ) {
}
