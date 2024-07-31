package nl.hu.inno.humc.roostering.consumer;

import nl.hu.inno.humc.roostering.domain.exception.MedewerkerNotFoundException;
import nl.hu.inno.humc.roostering.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.roostering.domain.exception.RoosterNotFoundException;
import nl.hu.inno.humc.roostering.producer.Producer;
import nl.hu.inno.humc.roostering.producer.message.MiddelKwijtMessage;
import nl.hu.inno.humc.roostering.producer.message.ResponseMessage;
import nl.hu.inno.humc.roostering.producer.message.StartOnderzoekMessage;
import nl.hu.inno.humc.roostering.application.RoosterService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;

@Component
public class Consumer {
    private final RoosterService roosterService;
    private final Producer producer;

    public Consumer(RoosterService roosterService, Producer producer) {
        this.roosterService = roosterService;
        this.producer = producer;
    }

    @RabbitListener(queues = { "middelkwijt-queue" })
    public void middelKwijt(MiddelKwijtMessage middelKwijtMessage){
        this.roosterService.annuleerAfsprakenVanMiddel(middelKwijtMessage.middelId);
    }

    @RabbitListener(queues = { "startonderzoek-queue" })
    public void startOnderzoek(StartOnderzoekMessage startOnderzoekMessage) {
        try {
            this.roosterService.addRoosterpunt(
                    this.roosterService.getfirstRooster().getId(),
                    startOnderzoekMessage.beschrijving,
                    startOnderzoekMessage.ruimteNummer,
                    startOnderzoekMessage.onderzoekersId,
                    startOnderzoekMessage.beginDatum,
                    startOnderzoekMessage.eindDatum);
        }catch (NietBeschikbaarException | RoosterNotFoundException | DateTimeException | MedewerkerNotFoundException e){
            this.producer.responseStartOnderzoek(
                    new ResponseMessage(true, e.getMessage())
            );
        }

        this.producer.responseStartOnderzoek(
                new ResponseMessage("Onderzoek: " + startOnderzoekMessage.beschrijving + " is gestart.")
        );
    }
}
