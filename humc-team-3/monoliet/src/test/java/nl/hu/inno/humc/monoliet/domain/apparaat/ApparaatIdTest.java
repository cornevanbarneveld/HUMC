package nl.hu.inno.humc.monoliet.domain.apparaat;

import nl.hu.inno.humc.monoliet.domain.exception.IncorrectIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApparaatIdTest {

    @Test
    @DisplayName("Test if the id is checked correctly when given a correct id")
    void isIdCorrectWhenCorrect() {
        String correctApparaatlId = "1234AB";
        assertDoesNotThrow(() -> new ApparaatId(correctApparaatlId));
    }

    @Test
    @DisplayName("Test if the id is checked correctly when given an incorrect id")
    void isIdInCorrectWhenInCorrect() {
        String incorrectApparaatlId = "abc123def456";
        assertThrows(IncorrectIdException.class, () -> new ApparaatId(incorrectApparaatlId));
    }
}
