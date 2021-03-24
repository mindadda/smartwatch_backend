//package com.htlabs.smartwatch.service.impl;
//
//import com.htlabs.smartwatch.service.ConsumerService;
//import com.htlabs.smartwatch.service.ProducerService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class ConsumerServiceImpl implements ConsumerService {
//
//    private final Logger logger = LoggerFactory.getLogger(ProducerService.class);
//
//    @KafkaListener(topics = "users", groupId = "group_id")
//    public void consume(String message) throws IOException {
//        logger.info(String.format("#### -> Consumed message -> %s", message));
//    }
//
//}
