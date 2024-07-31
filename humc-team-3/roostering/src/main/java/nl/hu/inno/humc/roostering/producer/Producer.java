package nl.hu.inno.humc.roostering.producer;

import nl.hu.inno.humc.roostering.producer.message.MiddelMessage;
import nl.hu.inno.humc.roostering.producer.message.ResponseMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate template;

    public Producer(RabbitTemplate template){
        this.template = template;
    }

    public void responseStartOnderzoek(ResponseMessage message) {
        this.template.convertAndSend("startonderzoekresponse-queue", message);
    }

    public void verplaatsMiddel(MiddelMessage middelMessage) {
        this.template.convertAndSend("verplaatsmiddel-queue", middelMessage);
    }
}
