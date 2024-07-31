package nl.hu.inno.humc.onderzoek.consumer;

import nl.hu.inno.humc.onderzoek.presentation.dto.BeschikbareItemsDTO;
import nl.hu.inno.humc.onderzoek.presentation.dto.OnderzoekResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener( queues = "accesresponse-queue")
    public void consumeAccesResponse(BeschikbareItemsDTO beschikbareItemsDTO) {
        if (beschikbareItemsDTO.nietBeschikbareMiddelen() != null) {
            System.out.println("yippie");
        } else {
            System.out.println("boe");
        }
    }

    @RabbitListener( queues = "startonderzoekresponse-queue")
    public void consumeOnderzoekResponse(OnderzoekResponseDTO onderzoekResponseDTO) {
        System.out.println(onderzoekResponseDTO.message());
    }
}
