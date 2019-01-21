package com.demo.spring.business.service.consumer.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Slf4j
@Service
public class ProductListenerConfig {

    @RabbitListener(queues = "${rabbit.product.queue.name}", containerFactory = "productRabbitListenerContainerFactory")
    public void messageHandle(Message message){
        try {
            String meg = new String(message.getBody(), "UTF-8");
            log.info("message.body:{}", meg);
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            log.info("message.headers:{}", headers);
            String queue = message.getMessageProperties().getConsumerQueue();
            log.info("message.queue:{}", queue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
