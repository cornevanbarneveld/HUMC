package nl.hu.inno.humc.monoliet.presentation.dto;

import java.util.UUID;

public record MedewerkerDto(Long id, String voornaam, String initialen, String achternaam, String woonplaats, String adres, String postcode, String email, String telefoonnummer, String functie) {
}
