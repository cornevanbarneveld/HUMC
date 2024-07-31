package nl.hu.inno.humc.ruimtebeheer.domain.middel;

import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.Periode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeriodeTest {

    @Test
    @DisplayName("Test isproductGeldig with an invalid date")
    void isproductGeldigWhenInvalid() {

        Periode houdbaarheidsperiodeNietGeldig = new Periode(LocalDateTime.of(2019,8 ,24, 14,15,22),
                LocalDateTime.of(2019,8 ,25, 14,15,22));
        assertFalse(houdbaarheidsperiodeNietGeldig.isperiodeVerlopen());
    }

    @Test
    @DisplayName("Test isproductGeldig with a valid date")
    void isproductGeldigWhenvalid() {

        Periode houdbaarheidsperiodeNietGeldig = new Periode(LocalDateTime.of(2019,8 ,24, 14,15,22),
                LocalDateTime.of(2025,8 ,25, 14,15,22));
        assertTrue(houdbaarheidsperiodeNietGeldig.isperiodeVerlopen());
    }
}
