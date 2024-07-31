package nl.hu.inno.humc.ruimtebeheer.api;

import nl.hu.inno.humc.ruimtebeheer.api.dto.RoosterPuntenPeriodeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class RoosterPuntApiTest {

    private String roosteringURI = "http://localhost:8086/";

    @Autowired
    private RoosterPuntApi roosterPuntApi;

    @Test
    void testRoosterOphalen() {
        //Deze test faalt als er geen rooster bestaat, als de verbinding niet werkt of als de roosterPuntApi faalt
        try {
            roosterPuntApi.setRoosterId(roosteringURI);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testGetRoosterpuntenForPeriode() {
        //Deze test faalt als er geen rooster bestaat, als de verbinding niet werkt of als de roosterPuntApi faalt
        try {
            roosterPuntApi.getRoosterpuntenForPeriode("http://localhost:8086/", LocalDateTime.now(), LocalDateTime.now());
        } catch (Exception e) {
            fail();
        }
    }






}
