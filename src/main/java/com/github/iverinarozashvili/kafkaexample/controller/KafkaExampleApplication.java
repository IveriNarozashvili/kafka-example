package com.github.iverinarozashvili.kafkaexample.controller;

import com.github.iverinarozashvili.kafkaexample.dto.UserDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaExampleApplication {

    @KafkaListener(topics = "New_topic")
    public void messageListener(ConsumerRecord<Long, UserDTO> record){
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.topic());
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
    }

}
