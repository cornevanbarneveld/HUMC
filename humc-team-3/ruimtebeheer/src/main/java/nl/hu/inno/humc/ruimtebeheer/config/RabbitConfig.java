package nl.hu.inno.humc.ruimtebeheer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  RabbitConfig {

    @Bean
    public Queue accesResponseQueue(){
        return QueueBuilder.durable("requestacces-queue").build();
    }

    @Bean
    public Queue additemresponseQueue(){
        return QueueBuilder.durable("verplaatsmiddel-queue").build();
    }

    @Bean
    MessageConverter getConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
