package nl.hu.inno.humc.onderzoek.producer;

import nl.hu.inno.humc.onderzoek.presentation.dto.AccesRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccesRequestProducer{

    private RabbitTemplate template;

    public AccesRequestProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(AccesRequestDTO message){
        this.template.convertAndSend("requestacces-queue", message);
    }
}
