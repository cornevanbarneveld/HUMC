package nl.hu.inno.humc.monoliet.domain.ruimte.locatie;

import nl.hu.inno.humc.monoliet.domain.exception.IncorrectPostcodeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
