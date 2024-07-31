package nl.hu.inno.humc.ruimtebeheer.producer;

import nl.hu.inno.humc.ruimtebeheer.producer.message.BeschikbareItemsMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccesResponseProducer implements MessageProducer<BeschikbareItemsMessage> {
    private RabbitTemplate template;

    public AccesResponseProducer(RabbitTemplate template){
        this.template = template;
    }

    @Override
    public void sendMessage(BeschikbareItemsMessage message){
        this.template.convertAndSend("accesresponse-queue", message);
    }
}
