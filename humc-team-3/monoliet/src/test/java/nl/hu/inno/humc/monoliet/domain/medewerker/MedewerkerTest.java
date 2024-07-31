package nl.hu.inno.humc.monoliet.domain.medewerker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedewerkerTest {
    public Naam naam;
    public AdresGegevens adresGegevens;
    public Email email;
    public Telefoonnummer telefoonnummer;
    public Medewerker medewerker;

    @BeforeEach
    public void InitializeCorrectMedewerker(){
        naam = new Naam("Ruben", "R", "van Rooijen");
        adresGegevens = new AdresGegevens("Bunschoten", "iets", "1111AA");
        email = new Email("rubenvanrooijen23@icloud.com");
        telefoonnummer = new Telefoonnummer("0611111111");
        medewerker = new Medewerker(naam, adresGegevens, email, telefoonnummer, "arts");
    }

    @Test
    @DisplayName("Medewerker is created correct if all the values are correct.")
    public void createMedewerkerObject(){
        assertEquals(naam, medewerker.getNaam());
        assertEquals(adresGegevens, medewerker.getAdresGegevens());
        assertEquals(email.getValue(), medewerker.getEmail());
        assertEquals(telefoonnummer.getValue(), medewerker.getTelefoonnummer());
        assertEquals(Functie.ARTS, medewerker.getFunctie());
    }

    @Test
    @DisplayName("Medewerker constructor throws error when an invalid function is entered.")
    public void medewerkersSetFunctions(){
        Naam naam2 = new Naam("Test", "T", "van Testen");
        AdresGegevens adresGegevens2 = new AdresGegevens("Spakenburg", "nog iets", "2222BB");
        Email email2 = new Email("rubenvanrooijen23@outlook.com");
        Telefoonnummer telefoonnummer2 = new Telefoonnummer("0622222222");

        this.medewerker.setNaam(naam2);
        this.medewerker.setEmail(email2);
        this.medewerker.setFunctie(Functie.ASSISTENT);
        this.medewerker.setTelefoonnummer(telefoonnummer2);
        this.medewerker.setAdresGegevens(adresGegevens2);

        assertEquals(naam2, medewerker.getNaam());
        assertEquals(adresGegevens2, medewerker.getAdresGegevens());
        assertEquals(email2.getValue(), medewerker.getEmail());
        assertEquals(telefoonnummer2.getValue(), medewerker.getTelefoonnummer());
        assertEquals(Functie.ASSISTENT, medewerker.getFunctie());
    }

    @Test
    @DisplayName("Medewerker constructor throws error when an invalid function is entered.")
    public void medewerkersFunctionIsNotValid(){
        Naam naam = new Naam("Ruben", "R", "van Rooijen");
        AdresGegevens adresGegevens = new AdresGegevens("Bunschoten", "iets", "1111AA");
        Email email = new Email("rubenvanrooijen23@icloud.com");
        Telefoonnummer telefoonnummer = new Telefoonnummer("0611111111");

        assertThrows(IllegalArgumentException.class, () -> new Medewerker(naam, adresGegevens, email, telefoonnummer, "artsNotValid"));
    }


}