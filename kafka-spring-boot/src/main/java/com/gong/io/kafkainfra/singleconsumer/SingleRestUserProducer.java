package com.gong.io.kafkainfra.singleconsumer;

import com.gong.io.kafkainfra.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class SingleRestUserProducer {

    @Value("${user.topic}")
    private String userTopic;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        kafkaTemplate.send(userTopic, new User(name, age));
    }

}