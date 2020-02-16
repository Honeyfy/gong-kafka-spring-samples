package com.gong.io.kafkainfra.batchconsumer;

import com.gong.io.kafkainfra.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/user")
public class BatchRestUserProducer {

    @Value("${user.topic}")
    private String userTopic;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @PostMapping(value = "/publish-batch")
    public void sendBatchMessageToKafkaTopic(@RequestParam("user-prefix") String userPrefix,
                                             @RequestParam("initial-age") Integer initialAge,
                                             @RequestParam("amount") Integer amount) {
        IntStream.range(0, amount)
                .mapToObj(num -> new User(userPrefix, initialAge + num))
                .forEach(this::sendMessage);
    }

    private void sendMessage(User user) {
        kafkaTemplate.send(userTopic, user);
    }
}