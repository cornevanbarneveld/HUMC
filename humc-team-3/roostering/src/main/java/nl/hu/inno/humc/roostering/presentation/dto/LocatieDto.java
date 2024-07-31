package nl.hu.inno.humc.roostering.presentation.dto;

public record LocatieDto(
        String postcode, String straatnaam, int huisnummer, int ruimtenummer, int halnummer){}

