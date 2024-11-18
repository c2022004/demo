package com.example.projectsale.kafka.controller;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.kafka.dto.Customer;
import com.example.projectsale.kafka.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + "/kafka")
public class KafkaProducerController {

    private final KafkaMessageService kafkaMessageService;

    @GetMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        kafkaMessageService.sendMessage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody Customer customer) {
        kafkaMessageService.sendMessage(customer);
        return new ResponseEntity<>("Send success", HttpStatus.OK);
    }
}
