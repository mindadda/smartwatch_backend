package com.htlabs.smartwatch.kafka;

import com.htlabs.smartwatch.Constants;
import com.htlabs.smartwatch.entity.Model;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Generate sample messages.
 *
 * Use: GET /kafla/sample/{amount}.
 */
@RestController
@RequestMapping(value = "/api/kafka")
public class ProducerController {

	private static final Logger LOG = LoggerFactory.getLogger(ProducerController.class);

	private ProducerCallback producerCallback = new ProducerCallback();

	@Autowired
	private KafkaTemplate<String, Model> kafkaTemplate;

	@RequestMapping(value = "/sample/{amount}", method = RequestMethod.GET)
	public void generateMessages(@PathVariable("amount") Integer amount) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String string  = dateFormat.format(new Date());

		IntStream.range(0, amount)
			.peek(i -> this.waitFor(1))
			.mapToObj(i -> new Model(UUID.randomUUID().toString() , "12AB34" , String.valueOf(i) ,string , string))
			.forEach(this::sendToKafka);
	}

	private void sendToKafka(Model model) {
		this.kafkaTemplate
			.send(Constants.KAFKA_TOPIC, UUID.randomUUID().toString(), model)
			.addCallback(this.producerCallback);
	}

	private void waitFor(int seconds) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Future<Void> future = scheduler.schedule(() -> null, seconds, TimeUnit.SECONDS);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	class ProducerCallback implements ListenableFutureCallback<SendResult<String, Model>> {

		@Override
		public void onSuccess(SendResult<String, Model> result) {
			RecordMetadata record = result.getRecordMetadata();
			LOG.info("Sending {} {} to topic {} - partition {}",
					result.getProducerRecord().key(),
					result.getProducerRecord().value(),
					result.getProducerRecord().topic(),
					record.partition());
		}

		@Override
		public void onFailure(Throwable ex) {
			LOG.error("Producer Failure", ex);
		}
	}

}
