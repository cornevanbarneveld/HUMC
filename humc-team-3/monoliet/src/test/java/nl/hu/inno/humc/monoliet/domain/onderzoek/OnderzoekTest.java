package nl.hu.inno.humc.monoliet.domain.onderzoek;

import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertSame;

class OnderzoekTest {

    @Test
    @DisplayName("test if the onderzoek state change to actife only happens when allowed to")
    void checkCorrectStateChange() {
        Onderzoek onderzoek = new Onderzoek();
        assertSame(OnderzoekStatus.CONCEPT, onderzoek.getOnderzoekStatus());

        onderzoek.changeOnderzoekStatus("ACTIEF");
        assertSame(OnderzoekStatus.CONCEPT, onderzoek.getOnderzoekStatus());

        onderzoek.setBudget(1000.0);
        Medewerker medewerker = new Medewerker();
        onderzoek.AddResearcher(medewerker);
        onderzoek.setBeschrijving("test");
        onderzoek.setPeriode(LocalDateTime.of(2024,8 ,24, 14,15,22), LocalDateTime.of(2025,8 ,24, 14,15,22));

        onderzoek.changeOnderzoekStatus("ACTIEF");
        assertSame(OnderzoekStatus.ACTIEF, onderzoek.getOnderzoekStatus());
    }

}