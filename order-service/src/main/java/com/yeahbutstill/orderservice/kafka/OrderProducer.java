package com.yeahbutstill.orderservice.kafka;

import com.yeahbutstill.basedomains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class OrderProducer {

    private final NewTopic newTopic;

    private final KafkaTemplate<String, OrderEvent> stringOrderEventKafkaTemplate;

    public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> stringOrderEventKafkaTemplate) {
        this.newTopic = newTopic;
        this.stringOrderEventKafkaTemplate = stringOrderEventKafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {

        log.info("Order event => {}", orderEvent);

        // create Message
        Message<OrderEvent> orderEventMessage = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        stringOrderEventKafkaTemplate.send(orderEventMessage);

    }

}
