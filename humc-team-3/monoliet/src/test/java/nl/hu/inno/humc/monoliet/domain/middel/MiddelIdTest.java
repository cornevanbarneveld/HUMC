package nl.hu.inno.humc.monoliet.domain.middel;

import nl.hu.inno.humc.monoliet.domain.exception.IncorrectIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiddelIdTest {

    @Test
    @DisplayName("Test if the id is checked correctly when given a correct id")
    void isIdCorrectWhenCorrect() {
        String correctMiddelId = "1234AB";
        assertDoesNotThrow(() -> new MiddelId(correctMiddelId));
    }

    @Test
    @DisplayName("Test if the id is checked correctly when given an incorrect id")
    void isIdInCorrectWhenInCorrect() {
        String incorrectId = "abc123def456";
        assertThrows(IncorrectIdException.class, () -> new MiddelId(incorrectId));
    }
}
