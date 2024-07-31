package nl.hu.inno.humc.roostering.api;

import nl.hu.inno.humc.roostering.presentation.dto.LaboratoriumDto;
import nl.hu.inno.humc.roostering.presentation.dto.MiddelDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Api {

    public MiddelDto getMiddelById(Long id) {
        RestTemplate rt = new RestTemplate();

        return rt.getForEntity("http://localhost:8085/middel/" + id, MiddelDto.class).getBody();
    }

    public LaboratoriumDto getLaboratoriumByName(String naam) {
        RestTemplate rt = new RestTemplate();
        System.out.println(naam);

        return rt.getForEntity("http://localhost:8085/laboratorium/" + naam, LaboratoriumDto.class).getBody();
    }
}
