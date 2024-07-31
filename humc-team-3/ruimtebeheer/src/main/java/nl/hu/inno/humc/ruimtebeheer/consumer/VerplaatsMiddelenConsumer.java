package nl.hu.inno.humc.ruimtebeheer.consumer;

import nl.hu.inno.humc.ruimtebeheer.application.RuimteService;
import nl.hu.inno.humc.ruimtebeheer.consumer.message.VerplaatsMiddelenMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerplaatsMiddelenConsumer implements MessageConsumer<VerplaatsMiddelenMessage> {

    @Autowired
    private RuimteService ruimteService;

    @Override
    @RabbitListener(queues = "verplaatsmiddel-queue")
    public void consume(VerplaatsMiddelenMessage verplaatsMiddelenMessage) {
        try {
            ruimteService.addVerplaatsMiddelNotification(verplaatsMiddelenMessage.ruimteId(), verplaatsMiddelenMessage.middelId(), verplaatsMiddelenMessage.beginDatum(), verplaatsMiddelenMessage.eindDatum());
        } catch (Exception e) {

        }
    }
}
