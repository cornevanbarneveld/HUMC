package nl.hu.inno.humc.monoliet.domain.middel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HoudbaarheidsperiodeTest {

    @Test
    @DisplayName("Test isproductGeldig with an invalid date")
    void isproductGeldigWhenInvalid() {

        Houdbaarheidsperiode houdbaarheidsperiodeNietGeldig = new Houdbaarheidsperiode(LocalDateTime.of(2019,8 ,24, 14,15,22),
                LocalDateTime.of(2019,8 ,25, 14,15,22));
        assertFalse(houdbaarheidsperiodeNietGeldig.isproductGeldig());
    }

    @Test
    @DisplayName("Test isproductGeldig with a valid date")
    void isproductGeldigWhenvalid() {

        Houdbaarheidsperiode houdbaarheidsperiodeNietGeldig = new Houdbaarheidsperiode(LocalDateTime.of(2019,8 ,24, 14,15,22),
                LocalDateTime.of(2025,8 ,25, 14,15,22));
        assertTrue(houdbaarheidsperiodeNietGeldig.isproductGeldig());
    }
}
