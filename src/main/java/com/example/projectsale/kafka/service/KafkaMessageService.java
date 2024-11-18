package com.example.projectsale.kafka.service;

import com.example.projectsale.kafka.dto.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("project-sale-topic", message);
        future.whenComplete((r, e) -> {
            if (e != null) {
                log.error(e.getMessage());
            } else {
                log.info("Message sent successfully : {}", message);
            }
        });
    }

    public void sendMessage(Customer customer) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("project-sale-topic-4", customer);
        future.whenComplete((r, e) -> {
            if (e != null) {
                log.error(e.getMessage());
            } else {
                log.info("Customer sent successfully : {}", customer.toString());
            }
        });
    }

}
