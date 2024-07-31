package nl.hu.inno.humc.ruimtebeheer.producer;

import nl.hu.inno.humc.ruimtebeheer.producer.message.MiddelAfwezigMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MiddelAfwezigProducer implements MessageProducer<MiddelAfwezigMessage> {
    private RabbitTemplate template;

    public MiddelAfwezigProducer(RabbitTemplate template){
        this.template = template;
    }

    @Override
    public void sendMessage(MiddelAfwezigMessage middelAfwezigMessage){
        this.template.convertAndSend("middelkwijt-queue", middelAfwezigMessage);
    }
}
