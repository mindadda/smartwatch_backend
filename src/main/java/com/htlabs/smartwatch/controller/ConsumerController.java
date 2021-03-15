// package com.htlabs.smartwatch.controller;

// import com.htlabs.smartwatch.Constants;
// import com.htlabs.smartwatch.entity.Model;
// import com.htlabs.smartwatch.entity.Panel;
// import com.htlabs.smartwatch.repository.ModelRepository;
// import com.htlabs.smartwatch.repository.PanelRepository;
// import com.htlabs.smartwatch.utils.ErrorMessages;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ResponseStatusException;

// import java.util.Date;

// @Component
// @Slf4j
// public class ConsumerController {

// 	private static final Logger LOG = LoggerFactory.getLogger(ConsumerController.class);

// 	@Autowired
// 	private SimpMessagingTemplate webSocket;

// 	@Autowired
// 	private ModelRepository modelRepository;

// 	@Autowired
// 	private PanelRepository panelRepository;

// 	@KafkaListener(topics = Constants.KAFKA_TOPIC)
// 	public void processMessage(ConsumerRecord<String, Model> cr, @Payload Model content) {
// 		LOG.info("Received content from Kafka: {} {} {}", content.getPanelId() , content.getSensorId() , content.getSensorValue());

// 		Model model = new Model(content.getPanelId() , content.getSensorId() , content.getSensorValue() ,
// 								content.getCurrentTime() , content.getUpdatedTime());
// 		modelRepository.save(model);

// 		String sensId = content.getSensorId();
// 		String sensorId = panelRepository.findSensorId(sensId);
// 		Panel panel = panelRepository.findBySensorId(sensId);
// 		String currentValue = panel.getCurrentValue();
// 		if (sensorId == null) {
// 			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_PANEL);

// //			panel.setPanelId(content.getPanelId());
// //			panel.setCurrentValue(content.getSensorValue());
// //			panel.setSensorId(content.getSensorId());
// //			panel.setCurrentTime(content.getCurrentTime());
// //			panel.setCurrentUpdatedTime(content.getUpdatedTime());
// //
// //			panel.setCreatedAt(new Date());
// //			panel.setUpdatedAt(new Date());
// //			panelRepository.save(panel);
// 		}
// 		else {
// 			if (sensorId.equals(sensId)) {
// 				if (currentValue == null) {
// 					panel.setCurrentValue(content.getSensorValue());
// 					panel.setCurrentTime(content.getCurrentTime());
// 					panel.setCurrentUpdatedTime(content.getCurrentTime());
// 				} else {
// 					if (content.getSensorValue().equals(currentValue)) {
// 						panel.setCurrentUpdatedTime(content.getCurrentTime());
// 					} else {
// 						panel.setPreviousValue(panel.getCurrentValue());
// 						panel.setPreviousTime(panel.getCurrentTime());
// 						panel.setPreviousUpdatedTime(panel.getCurrentUpdatedTime());

// 						panel.setCurrentValue(content.getSensorValue());
// 						panel.setCurrentTime(content.getCurrentTime());
// 						panel.setCurrentUpdatedTime(content.getUpdatedTime());

// 					}
// 				}
// 			}
// 			panel.setUpdatedAt(new Date());
// 			panelRepository.save(panel);
// 		}

// 		this.webSocket.convertAndSend(Constants.WEBSOCKET_DESTINATION, content.getSensorValue());

// 	}

// }
