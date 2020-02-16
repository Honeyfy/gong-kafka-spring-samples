package com.gong.io.kafkainfra.batchconsumer;

import com.gong.io.kafkainfra.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchUserConsumer {

    @KafkaListener(topics = "${user.topic}", groupId = "group_2")
    public void consume(List<ConsumerRecord<String, User>> records) {
        log.info("Consuming {} records", records.size());
        records.forEach(this::logRecord);
    }

    private void logRecord(ConsumerRecord<String, User> record) {
        log.info("Consumed message -> {}, {}", record.key(), record.value());
    }
}