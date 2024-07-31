package nl.hu.inno.humc.ruimtebeheer.domain.ruimte.locatie;

import nl.hu.inno.humc.ruimtebeheer.domain.exception.IncorrectPostcodeException;
import nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte.locatie.Postcode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostcodeTest {


    @Test
    @DisplayName("Test is a postcode is checked correctly when given a correct postcode")
    void isPostcodeCorrectWhenCorrect() {
        String correctePostcode = "1234AB";
        assertDoesNotThrow(() -> new Postcode(correctePostcode));
    }

    @Test
    @DisplayName("Test is a postcode is checked correctly when given a incorrect postcode")
    void isInPostcodeCorrectWhenInCorrect() {
        String incorrectePostcode = "1234 AB";
        assertThrows(IncorrectPostcodeException.class, () -> new Postcode(incorrectePostcode));
    }
}
