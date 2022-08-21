package com.yeahbutstill.orderservice.kafka;

import com.yeahbutstill.basedomains.dto.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OrderProducer {

    private NewTopic newTopic;

    private KafkaTemplate<String, OrderEvent> stringOrderEventKafkaTemplate;

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
