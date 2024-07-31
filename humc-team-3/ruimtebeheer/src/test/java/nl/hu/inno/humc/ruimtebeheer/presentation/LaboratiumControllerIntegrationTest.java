package nl.hu.inno.humc.ruimtebeheer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.humc.ruimtebeheer.data.RuimteRepo;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Laboratorium.LaboratoriumDto;
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


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LaboratiumControllerIntegrationTest {


    @MockBean
    private RuimteRepo ruimteRepository;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test if a laboratorium is created correctly")
    void createLaboratorium() throws Exception {
        LaboratoriumDto expectedLaboratoriumDto = new LaboratoriumDto(1L,"1234AB", "straatnaam",1, 1,1,
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



}
