package nl.hu.inno.humc.roostering.presentation.dto;

public record MedewerkerDto(Long id, String voornaam, String initialen, String achternaam, String woonplaats, String adres, String postcode, String email, String telefoonnummer, String functie) {
}
