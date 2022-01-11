package com.stark.springbootkafkaproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventProducer {

    @Autowired
    private Environment environment;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        System.out.println("Message " + msg);
        kafkaTemplate.send(environment.getProperty("topicname"), msg);
    }

}
