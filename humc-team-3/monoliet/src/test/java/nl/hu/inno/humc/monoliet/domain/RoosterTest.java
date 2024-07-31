package nl.hu.inno.humc.monoliet.domain;

import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterDto;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterpuntDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoosterTest {

    @Test
    @DisplayName("A rooster object can be made")
    void RoosterExist() {
        Rooster rooster = new Rooster();

        assertEquals(0, rooster.getAfspraken().size());
    }

    @Test
    @DisplayName("Roosterpunt can be added to a rooster")
    void RoosterpuntAddedExist() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();

        rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));

        assertEquals(1, rooster.getAfspraken().size());
    }

    @Test
    @DisplayName("Roosterpunt can not be added to a rooster")
    void RoosterpuntAddErrors() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));

        //Medewerker is not available.
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(4L), LocalDateTime.now().plusHours(1L)));

        //begintijd of/en eindtijd is niet geldig.
        assertThrows(DateTimeException.class, () ->
                rooster.addRoosterpunt(organisator, LocalDateTime.now().minusMinutes(2L), LocalDateTime.now().plusHours(1L)));
    }

    @Test
    @DisplayName("Apparaat can be added to a roosterpunt")
    void AddApparaatToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        Roosterpunt roosterpunt = rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Apparaat apparaat = new Apparaat();

        rooster.addApparaatToRoosterpunt(apparaat, roosterpunt);

        assertEquals(1, roosterpunt.getApparaten().size());
    }

    @Test
    @DisplayName("Locatie can be added to a roosterpunt")
    void AddLocatieToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        Roosterpunt roosterpunt = rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Laboratorium laboratorium = new Laboratorium();

        rooster.addLocatieToRoosterpunt(laboratorium, roosterpunt);

        assertEquals(1, roosterpunt.getLocaties().size());
    }

    @Test
    @DisplayName("Medewerker can be added to a roosterpunt")
    void AddBetrokkeneToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        Roosterpunt roosterpunt = rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Medewerker medewerker = new Medewerker();

        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);

        assertEquals(1, roosterpunt.getBetrokkenen().size());
    }

    @Test
    @DisplayName("Apparaat/locatie/medewerker can't be added to a roosterpunt")
    void RoosterpuntAddItemsToOtherListsErrors() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        Roosterpunt roosterpunt = rooster.addRoosterpunt(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Apparaat apparaat = new Apparaat();
        Laboratorium laboratorium = new Laboratorium();
        Medewerker medewerker = new Medewerker();
        rooster.addApparaatToRoosterpunt(apparaat, roosterpunt);
        rooster.addLocatieToRoosterpunt(laboratorium, roosterpunt);
        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);
        Medewerker otherMedewerker = new Medewerker();
        Roosterpunt otherRoosterpunt = rooster.addRoosterpunt(otherMedewerker, LocalDateTime.now().plusMinutes(4L), LocalDateTime.now().plusHours(1L));

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addApparaatToRoosterpunt(apparaat, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addApparaatToRoosterpunt(apparaat, roosterpunt));

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addLocatieToRoosterpunt(laboratorium, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addLocatieToRoosterpunt(laboratorium, roosterpunt));

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addBetrokkeneToRoosterpunt(medewerker, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt));
    }

}