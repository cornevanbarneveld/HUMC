package nl.hu.inno.humc.ruimtebeheer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.hu.inno.humc.ruimtebeheer.data.RuimteRepo;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelRuimteDto;
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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RuimteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuimteRepo ruimteRepository;

    @Test
    @DisplayName("Test if an Apparaat is added correctly")
    void addMiddel() throws Exception {
        Ruimte ruimte = new Ruimte("1234AB", "straat1",1,1,1, "ruimte" );

        when(ruimteRepository.findById((long) 1)).thenReturn(Optional.of(ruimte));

        MiddelRuimteDto middelRuimteDto = new MiddelRuimteDto("vitamineD", "pilletjes die vitamineD bevatten", null,"sda76abd",
                (long) 1, LocalDateTime.of(2019,8,24,14,15,22), LocalDateTime.of(2025,8,24,14,15,22));

        String middelBody = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(middelRuimteDto);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/ruimte/middel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(middelBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("vitamineD")))
                .andExpect(jsonPath("$.beschrijving",is("pilletjes die vitamineD bevatten")))
                .andExpect(jsonPath("$.registratieDatum", is("2019-08-24T14:15:22")))
                .andExpect(jsonPath("$.houdbaarheidsDatum",is("2025-08-24T14:15:22")));


    }

}
