package nl.hu.inno.humc.monoliet.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.humc.monoliet.application.MedewerkerService;
import nl.hu.inno.humc.monoliet.data.MedewerkerRepository;
import nl.hu.inno.humc.monoliet.domain.medewerker.*;
import nl.hu.inno.humc.monoliet.presentation.dto.MedewerkerDto;
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
class MedewerkerControllerIntegrationTest {
    @MockBean
    private MedewerkerRepository medewerkerRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("Test if a medewerker is created correctly.")
    void createMedewerker() throws Exception {
        MedewerkerDto medewerkerDto = new MedewerkerDto((long) 1, "Ruben", "R", "van Rooijen", "Bunschoten", "iets", "1111AA", "iets@mail.com", "0611111111", "arts");

        String medewerkerBody = new ObjectMapper().writeValueAsString(medewerkerDto);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/medewerker")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medewerkerBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voornaam", is("Ruben")))
                .andExpect(jsonPath("$.woonplaats", is("Bunschoten")))
                .andExpect(jsonPath("$.email", is("iets@mail.com")))
                .andExpect(jsonPath("$.telefoonnummer", is("0611111111")))
                .andExpect(jsonPath("$.functie",is("ARTS")))
        ;
    }

    @Test
    void updateMedewerkersEmail() throws Exception {
        MedewerkerDto medewerkerDto = new MedewerkerDto((long) 1, "Ruben", "R", "van Rooijen", "Bunschoten", "iets", "1111AA", "iets@mail.com", "0611111111", "arts");
        Naam naam = new Naam("Ruben", "R", "van Rooijen");
        AdresGegevens adresGegevens = new AdresGegevens("Bunschoten", "iets", "1111AA");
        Email email = new Email("rubenvanrooijen23@icloud.com");
        Telefoonnummer telefoonnummer = new Telefoonnummer("0611111111");
        Medewerker medewerker = new Medewerker(naam, adresGegevens, email, telefoonnummer, "arts");

        when(medewerkerRepository.findById((long) 1)).thenReturn(Optional.of(medewerker));

        String medewerkerBody = new ObjectMapper().writeValueAsString(medewerkerDto);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/medewerker/update/1/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medewerkerBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("iets@mail.com")))
        ;
    }
}