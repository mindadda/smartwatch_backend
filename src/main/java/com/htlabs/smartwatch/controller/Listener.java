package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.Constants;
import com.htlabs.smartwatch.entity.Model;
import com.htlabs.smartwatch.entity.Panel;
import com.htlabs.smartwatch.repository.ModelRepository;
import com.htlabs.smartwatch.repository.PanelRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Listener {

	private static final Logger LOG = LoggerFactory.getLogger(Listener.class);

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private PanelRepository panelRepository;

	@KafkaListener(topics = Constants.KAFKA_TOPIC)
	public void processMessage(ConsumerRecord<String, Model> cr, @Payload Model content) {
		LOG.info("Received content from Kafka: {} {} {}", content.getPanelId() , content.getSensorId() , content.getSensorValue());

		Model model = new Model(content.getPanelId() , content.getSensorId() , content.getSensorValue() ,
								content.getCurrentTime() , content.getUpdatedTime());
		modelRepository.save(model);

		Panel panel = new Panel();
		Integer i = 0;
		String sensId = content.getSensorId();
		String sensorId = panelRepository.findSensorId(sensId);
		if (sensorId == null) {
//			panel.setPanelName("Panel A" + i);

			panel.setPanelId(content.getPanelId());
			panel.setCurrentValue(content.getSensorValue());
			panel.setSensorId(content.getSensorId());
			panel.setCurrentTime(content.getCurrentTime());
			panel.setCurrentUpdatedTime(content.getUpdatedTime());

			panel.setCreatedAt(new Date());
			panel.setUpdatedAt(new Date());
			panelRepository.save(panel);
			i++;
		}
		else{
			Panel panel1 = panelRepository.findBySensorId(sensId);
			if (sensorId.equals(sensId)){
				panel1.setCurrentUpdatedTime(content.getCurrentTime());

				panel1.setUpdatedAt(new Date());
				panelRepository.save(panel1);
				i++;
			}
			else{
				panel1.setCurrentValue(content.getSensorValue());
				panel1.setCurrentTime(content.getCurrentTime());
				panel1.setCurrentUpdatedTime(content.getUpdatedTime());

				panel1.setPreviousValue(panel.getCurrentValue());
				panel1.setPreviousTime(panel.getCurrentTime());
				panel1.setPreviousUpdatedTime(panel.getCurrentUpdatedTime());

				panel1.setUpdatedAt(new Date());
				panelRepository.save(panel1);
				i++;
			}
		}

		this.webSocket.convertAndSend(Constants.WEBSOCKET_DESTINATION, content.getSensorValue());

	}

}
