package com.kaistsw.arch.domain.batch.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KafkaProducer<T> {

    private Producer<String, T> producer;
    private String topicName = "job_item_topic";

    public void send(T dto) {
        producer.send(new ProducerRecord<>(topicName,dto));
    }
}
