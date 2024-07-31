package nl.hu.inno.humc.monoliet.domain;

import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.domain.middel.MiddelId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class InventarisTest {

    @Test
    @DisplayName("test if the function adds the invalid middelen to the overdedatumMiddelen")
    void checkMiddelenOverDeDatum() {
        Inventaris inventaris = new Inventaris();
        Middel middel = new Middel("vitamineD", "pilletjes doe vitamineD bevatten",null, "12345AB", LocalDateTime.of(2019,8 ,24, 14,15,22),
                LocalDateTime.of(2019,8 ,25, 14,15,22));

        ArrayList<MiddelId> overDeDatumMiddelen = new ArrayList<>();
        overDeDatumMiddelen.add(middel.getId());
        inventaris.addMiddel(middel);

        inventaris.checkMiddelenOverDeDatum();
        assertEquals(inventaris.getOverDeDatumMiddelen(), overDeDatumMiddelen);
    }
}
