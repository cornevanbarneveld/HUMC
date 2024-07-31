package nl.hu.inno.humc.monoliet.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.humc.monoliet.application.MedewerkerService;
import nl.hu.inno.humc.monoliet.data.RuimteRepository;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.medewerker.*;
import nl.hu.inno.humc.monoliet.presentation.dto.Laboratorium.BeheerderDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Laboratorium.LaboratoriumDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LaboratiumControllerIntegrationTest {


    @MockBean
    private RuimteRepository ruimteRepository;

    @MockBean
    private MedewerkerService medewerkerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test if a laboratorium is created correctly")
    void createLaboratorium() throws Exception {
        LaboratoriumDto expectedLaboratoriumDto = new LaboratoriumDto("1234AB", "straatnaam",1, 1,1,
                "lab1", "onderzoek", null  );


        String labBody = new ObjectMapper().writeValueAsString(expectedLaboratoriumDto);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/laboratorium")
                .contentType(MediaType.APPLICATION_JSON)
                .content(labBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("onderzoek")));
    }


    @Test
    @DisplayName("Test if a beheerder is replaced correctly")
    void replaceBeheerder() throws Exception {
        Naam naamObject = new Naam("henk", "van", "achteren");
        AdresGegevens adresGegevens = new AdresGegevens("utrecht", "straat1", "1234AB");
        Email emailObject = new Email("henk@email");
        Telefoonnummer telefoonnummerObject = new Telefoonnummer("0612345678");

        Medewerker medewerker = new Medewerker(naamObject,adresGegevens,emailObject,telefoonnummerObject, "arts" );
        medewerker.setMedewerkerId((long) 1);
        Laboratorium laboratorium = new Laboratorium("1234AB", "straatnaam",1, 1,1,
                "lab1", "onderzoek", null  );

        when(ruimteRepository.findById((long) 1)).thenReturn(Optional.of(laboratorium));
        when(medewerkerService.GetMedewerkerIfNotNull((long) 1)).thenReturn(medewerker);

        BeheerderDto beheerderDto = new BeheerderDto((long) 1, (long) 1);

        String labBody = new ObjectMapper().writeValueAsString(beheerderDto);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/laboratorium")
                .contentType(MediaType.APPLICATION_JSON)
                .content(labBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("onderzoek")))
                .andExpect(jsonPath("$.straatnaam",is("straatnaam")))
                .andExpect(jsonPath("$.huisnummer", is(1)))
                .andExpect(jsonPath("$.ruimtenummer",is(1)))
                .andExpect(jsonPath("$.halnummer", is(1)))
                .andExpect(jsonPath("$.naam",is("lab1")))
                .andExpect(jsonPath("$.type", is("onderzoek")))
                .andExpect(jsonPath("$.medewerker.id",is(1)))
                .andExpect(jsonPath("$.medewerker.naam.voornaam", is("henk")))
                .andExpect(jsonPath("$.medewerker.woonplaats",is("utrecht")))
                .andExpect(jsonPath("$.medewerker.adres", is("straat1")))
                .andExpect(jsonPath("$.medewerker.email",is("henk@email")))
                .andExpect(jsonPath("$.medewerker.telefoonnummer", is("0612345678")))
                .andExpect(jsonPath("$.medewerker.functie",is("ARTS")));

    }


}
