package nl.hu.inno.humc.onderzoek.producer;

import nl.hu.inno.humc.onderzoek.presentation.dto.RoosterOnderzoekDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartOnderzoekProducer {

    private RabbitTemplate template;

    public StartOnderzoekProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(RoosterOnderzoekDTO message){
        this.template.convertAndSend("startonderzoek-queue", message);
    }

}

