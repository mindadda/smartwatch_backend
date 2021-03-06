package com.htlabs.smartwatch.kafka;

import com.htlabs.smartwatch.Constants;
import com.htlabs.smartwatch.entity.Model;
import com.htlabs.smartwatch.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {

	private static final Logger LOG = LoggerFactory.getLogger(Listener.class);

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Autowired
	private ModelRepository modelRepository;

	@KafkaListener(topics = Constants.KAFKA_TOPIC)
	public void processMessage(ConsumerRecord<String, Model> cr, @Payload Model content) {
		LOG.info("Received content from Kafka: {} {} {}", content.getPanelId() , content.getSensorId() , content.getSensorValue());

		Model model = new Model(content.getPanelId() , content.getSensorId() , content.getSensorValue() ,
								content.getCurrentTime() , content.getUpdatedTime());
		modelRepository.save(model);
		this.webSocket.convertAndSend(Constants.WEBSOCKET_DESTINATION, content.getSensorValue());

	}

}
