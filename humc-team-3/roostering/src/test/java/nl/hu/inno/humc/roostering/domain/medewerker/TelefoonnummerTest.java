package nl.hu.inno.humc.roostering.domain.medewerker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TelefoonnummerTest {
    @Test
    @DisplayName("Telefoonnummer is valid")
    public void TelefoonnummerValid(){
        Telefoonnummer telefoonnummer = new Telefoonnummer("0611111111");

        assertEquals("0611111111", telefoonnummer.getValue());
    }

    @Test
    @DisplayName("Telefoonnummer is invalid")
    public void TelefoonnummerInvalid(){
        assertThrows(IllegalArgumentException.class, ()-> new Telefoonnummer("06"));
    }

}