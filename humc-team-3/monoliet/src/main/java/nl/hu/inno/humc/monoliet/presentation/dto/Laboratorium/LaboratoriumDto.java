package nl.hu.inno.humc.monoliet.presentation.dto.Laboratorium;

import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;

public record LaboratoriumDto(
        String postcode,
        String straatnaam,
        int huisnummer,
        int ruimtenummer,
        int halnummer,
        String naam,
        String type,
        Medewerker medewerker) {
}
