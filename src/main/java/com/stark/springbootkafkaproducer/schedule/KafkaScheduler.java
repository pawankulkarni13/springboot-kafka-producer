package com.stark.springbootkafkaproducer.schedule;

import com.stark.springbootkafkaproducer.producer.KafkaEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaScheduler {

    @Autowired
    KafkaEventProducer kafkaEventProducer;

    @Scheduled(fixedRate = 10000)
    public void sender(){
        String msg = UUID.randomUUID().toString();
        kafkaEventProducer.sendMessage("Hello !! Sending Id - " + msg);
    }
}
