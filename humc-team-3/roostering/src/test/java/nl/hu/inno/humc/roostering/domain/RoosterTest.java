package nl.hu.inno.humc.roostering.domain;

import nl.hu.inno.humc.roostering.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.roostering.domain.exception.RoosterpuntNotFoundException;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.domain.roosterpunt.Roosterpunt;
import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoosterTest {

    @Test
    @DisplayName("A rooster object can be made")
    void RoosterExist() {
        Rooster rooster = new Rooster();

        assertNotNull(rooster.getId());
        assertEquals(0, rooster.getAfspraken().size());
    }

    @Test
    @DisplayName("Roosterpunt can be added to a rooster")
    void RoosterpuntAddedExist() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));

        rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        assertEquals(1, rooster.getAfspraken().size());
    }

    @Test
    @DisplayName("Roosterpunt can be canceled.")
    void annuleerRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        rooster.annuleerRoosterpunt(roosterpunt);

        assertEquals(0, rooster.afspraken.size());
    }

    @Test
    @DisplayName("Roosterpunt can not be added to a rooster")
    void RoosterpuntAddErrors() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        Medewerker organisator2 = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        rooster.addRoosterpunt(roosterpuntId, "test", 1L);
        RoosterpuntId roosterpuntIdDateException = new RoosterpuntId(organisator, LocalDateTime.now().minusMinutes(2L), LocalDateTime.now().plusHours(1L));
        RoosterpuntId roosterpuntIdMedewerkerException = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(4L), LocalDateTime.now().plusHours(1L));
        RoosterpuntId roosterpuntIdRuimteException = new RoosterpuntId(organisator2, LocalDateTime.now().plusMinutes(4L), LocalDateTime.now().plusHours(1L));


        //Medewerker is not available.
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addRoosterpunt(roosterpuntIdMedewerkerException, "test", 1L));

        //begintijd of/en eindtijd is niet geldig.
        assertThrows(DateTimeException.class, () ->
                rooster.addRoosterpunt(roosterpuntIdDateException, "test", 1L));

        //ruimte is not available.
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addRoosterpunt(roosterpuntIdRuimteException, "test", 1L));
    }

    @Test
    @DisplayName("Get all roosterpunten of today in the rooster")
    void getRoosterpuntenVandaag() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));

        rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        assertEquals(1, rooster.getAfsprakenVandaag().size());
    }

    @Test
    @DisplayName("Get the roosterpunt by id")
    void getAfspraakBijRoosterpuntIdSamenstelling() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        RoosterpuntId roosterpuntId2 = new RoosterpuntId(organisator, LocalDateTime.now().plusHours(2L), LocalDateTime.now().plusHours(3L));
        rooster.addRoosterpunt(roosterpuntId2, "test", 2L);
        rooster.addRoosterpunt(roosterpuntId, "test", 1L);


        Roosterpunt roosterpunt = rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId);

        assertEquals(1L, roosterpunt.getRuimteNummer());
        assertEquals("test", roosterpunt.getBeschrijving());
    }

    @Test
    @DisplayName("throw exception when a afspraak is requested, but doesn't exist.")
    void geenAfspraakGevondenError() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));

        assertThrows(RoosterpuntNotFoundException.class, () ->
                rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId));
    }

    @Test
    @DisplayName("Middel can be added to a roosterpunt")
    void AddMiddelToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        rooster.addMiddelToRoosterpunt(1L, roosterpunt);

        assertEquals(1, roosterpunt.getMiddelen().size());
    }

    @Test
    @DisplayName("Locatie can be added to a roosterpunt")
    void setRuimteToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        rooster.setRoosterpuntRuimte(4L, roosterpunt);

        assertEquals(4, roosterpunt.getRuimteNummer());
    }

    @Test
    @DisplayName("Medewerker can be added to a roosterpunt")
    void AddBetrokkeneToRoosterpunt() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, "test", 1L);
        Medewerker medewerker = new Medewerker();

        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);

        assertEquals(1, roosterpunt.getBetrokkenen().size());
    }

    @Test
    @DisplayName("Get the afspraken by ruimte id.")
    void getAfsprakenVanRuimte() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        rooster.addRoosterpunt(roosterpuntId, "test", 1L);

        List<Roosterpunt> roosterpunten = rooster.getAfsprakenVanDeRuimte(1L);

        assertEquals(1, roosterpunten.size());
    }

    @Test
    @DisplayName("Apparaat/locatie/medewerker can't be added to a roosterpunt")
    void RoosterpuntAddItemsToOtherListsErrors() throws NietBeschikbaarException {
        Rooster rooster = new Rooster();
        Medewerker organisator = new Medewerker();
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, "test", 1L);
        Medewerker medewerker = new Medewerker();
        rooster.addMiddelToRoosterpunt(1L, roosterpunt);
        rooster.setRoosterpuntRuimte(1L, roosterpunt);
        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);
        Medewerker otherMedewerker = new Medewerker();
        RoosterpuntId otherRoosterpuntId = new RoosterpuntId(otherMedewerker, LocalDateTime.now().plusMinutes(4L), LocalDateTime.now().plusHours(1L));
        Roosterpunt otherRoosterpunt = rooster.addRoosterpunt(otherRoosterpuntId, "test", 2L);

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addMiddelToRoosterpunt(1L, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addMiddelToRoosterpunt(1L, roosterpunt));

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.setRoosterpuntRuimte(1L, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.setRoosterpuntRuimte(2L, roosterpunt));

        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addBetrokkeneToRoosterpunt(medewerker, otherRoosterpunt));
        assertThrows(NietBeschikbaarException.class, () ->
                rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt));
    }

}