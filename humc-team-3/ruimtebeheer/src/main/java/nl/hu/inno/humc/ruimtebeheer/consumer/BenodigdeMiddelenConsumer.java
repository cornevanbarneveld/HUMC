package nl.hu.inno.humc.ruimtebeheer.consumer;


import nl.hu.inno.humc.ruimtebeheer.application.BenodigdhedenService;
import nl.hu.inno.humc.ruimtebeheer.consumer.message.RequestBeschikbareItemsMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;


@Component
public class BenodigdeMiddelenConsumer implements MessageConsumer<RequestBeschikbareItemsMessage> {

    @Autowired
    private BenodigdhedenService benodigdhedenService;

    @Override
    @RabbitListener(queues = "requestacces-queue")
    public void consume(RequestBeschikbareItemsMessage requestBeschikbareItemsMessage) {


        HashMap<String, Integer> testMap = new HashMap<String, Integer>();
        // Add keys and values (Country, City)
        testMap.put("vitamineD", 2);
        RequestBeschikbareItemsMessage requestBeschikbareItemsMessage1 = new RequestBeschikbareItemsMessage("aa", testMap,
                LocalDateTime.of(2025,6,12,2,1,1), LocalDateTime.of(2026,6,12,2,1,1));
        System.out.println("hier");
        try {
            benodigdhedenService.checkBenodigdheden(requestBeschikbareItemsMessage1);
        } catch (Exception e) {
            benodigdhedenService.checkBenodigdhedenFailed();
        }

    }
}
