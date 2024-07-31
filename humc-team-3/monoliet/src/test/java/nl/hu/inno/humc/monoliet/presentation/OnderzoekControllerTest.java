package nl.hu.inno.humc.monoliet.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.humc.monoliet.application.OnderzoekService;
import nl.hu.inno.humc.monoliet.data.MedewerkerRepository;
import nl.hu.inno.humc.monoliet.data.OnderzoekRepository;
import nl.hu.inno.humc.monoliet.presentation.dto.OnderzoekDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OnderzoekControllerTest {

    @MockBean
    private OnderzoekService onderzoekService;

    @MockBean
    private MedewerkerRepository medewerkerRepository;

    @MockBean
    private OnderzoekRepository onderzoekRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOnderzoek() throws Exception {
        OnderzoekDTO onderzoekDTO = new OnderzoekDTO(1L, 100.0, null, null, "test", new ArrayList<>(), "CONCEPT");

        String body = new ObjectMapper().writeValueAsString(onderzoekDTO);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/onderzoek")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }


}