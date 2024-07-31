package nl.hu.inno.humc.roostering.domain.medewerker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {
    @Test
    @DisplayName("Email is valid")
    public void emailValid(){
        Email email = new Email("rubenvanrooijen23@icloud.com");

        assertEquals("rubenvanrooijen23@icloud.com", email.getValue());
    }

    @Test
    @DisplayName("Email is not valid")
    public void emailInvalid(){
        assertThrows(IllegalArgumentException.class, () -> new Email("Invalidmail"));
    }
}