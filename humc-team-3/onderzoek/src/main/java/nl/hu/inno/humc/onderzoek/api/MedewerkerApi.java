package nl.hu.inno.humc.onderzoek.api;

import nl.hu.inno.humc.onderzoek.api.dto.MedewerkerDto;
import nl.hu.inno.humc.onderzoek.domain.exception.MedewerkerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class MedewerkerApi {

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<MedewerkerDto> getMedewerkerById(Long id) {
        String uri = "http://roostering:8086/medewerker/" + id;
        try {
            return restTemplate.getForEntity(URI.create(uri), MedewerkerDto.class);
        } catch (MedewerkerNotFoundException e) {
            return null;
        }
    }
}
