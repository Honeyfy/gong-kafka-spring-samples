package com.gong.io.kafkainfra.singleconsumer;

import com.gong.io.kafkainfra.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class SingleUserConsumer {

    @KafkaListener(topics = "${user.topic}", groupId = "group_2")
    public void consume(ConsumerRecord<String, User> record) {
        log.info("Consumed message -> {}, {}", record.key(), record.value());
    }

    private int stam;

    public static void main(String[] args) {

    }
}