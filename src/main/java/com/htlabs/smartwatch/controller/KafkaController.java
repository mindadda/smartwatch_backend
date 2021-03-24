//package com.htlabs.smartwatch.controller;
//
//import com.htlabs.smartwatch.dto.ResponseDTO;
//import com.htlabs.smartwatch.service.ProducerService;
//import com.htlabs.smartwatch.utils.SuccessMessages;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/kafka")
//@Validated
//@Slf4j
//@Component
//public class KafkaController {
//
//    @Autowired
//    private ProducerService producer;
//
//    @Autowired
//    KafkaController(ProducerService producer) {
//        this.producer = producer;
//    }
//
//    @PostMapping(value = "/publish")
//    public ResponseDTO sendMessageToKafkaTopic(@RequestParam("message") String message) {
//        producer.sendMessage(message);
//        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.MESSAGE_CREATED , message));
//    }
//}
