package com.github.iverinarozashvili.kafkaexample;

import com.github.iverinarozashvili.kafkaexample.dto.Address;
import com.github.iverinarozashvili.kafkaexample.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс MessageController
 *
 * @author  Iveri Narozashvili
 * created 19.06.2020
 */
@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<Long, UserDTO> kafkaTemplate;

    @PostMapping
    public void send(Long msgId, UserDTO msg) {
        msg.setAddress(new Address("Russia", "Moscow", "Lenina", 2L, 28L));
        ListenableFuture<SendResult<Long, UserDTO>> future = kafkaTemplate.send("New_topic", msgId, msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
