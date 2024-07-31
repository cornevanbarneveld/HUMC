package nl.hu.inno.humc.ruimtebeheer.api;

import nl.hu.inno.humc.ruimtebeheer.api.dto.RoosterDto;
import nl.hu.inno.humc.ruimtebeheer.api.dto.RoosterPuntenPeriodeDto;
import nl.hu.inno.humc.ruimtebeheer.domain.exception.ServerFailedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Random;


@Component
public class RoosterPuntApi {

    private RestTemplate template = new RestTemplate();
    private String roosterId;

    public void setRoosterId(String uri) throws Exception {
        String requestUri = uri + "rooster";
        for (int i = 0; i < 5; i++) {
            try{
                //fallacy
                if (getRandomNumber() != 1) {
                    this.roosterId = template.getForEntity(URI.create(requestUri), RoosterDto.class).getBody().id();
                    continue;
                }
                throw new HttpServerErrorException(HttpStatusCode.valueOf(502));
            }catch (HttpServerErrorException serverError){
                System.out.println("Server faalt");
            }catch (Exception ex){
                throw new Exception("something went wrong");
            }
        }
    }

    public RoosterPuntenPeriodeDto getRoosterpuntenForPeriode(String hostAndport, LocalDateTime begindatum, LocalDateTime eindDatum) throws Exception {
        System.out.println("what");
        setRoosterId(hostAndport);
        String uri = hostAndport + roosterId + "/roosterpunt" + "/periode/" + begindatum.toString() + "/" + eindDatum.toString();
        System.out.println(uri);
        for (int i = 0; i < 5; i++) {
            try{
                //fallacy
                if (getRandomNumber() != 1) {
                    System.out.println("hier");
                    return template.getForEntity(URI.create(uri), RoosterPuntenPeriodeDto.class).getBody();
                }
                throw new HttpServerErrorException(HttpStatusCode.valueOf(502));

            }catch (HttpServerErrorException serverError){
                System.out.println("Server faalt");
            }catch (Exception ex){
                throw new Exception("something went wrong");
            }
        }
        throw new ServerFailedException("De server kon de request niet voltooien");
    }

    public String getRoosterId() {
        return roosterId;
    }

    public int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(3);
    }
}
