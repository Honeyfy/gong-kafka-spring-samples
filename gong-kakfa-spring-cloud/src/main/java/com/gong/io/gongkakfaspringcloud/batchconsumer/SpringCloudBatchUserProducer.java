package com.gong.io.gongkakfaspringcloud.batchconsumer;

import com.gong.io.gongkakfaspringcloud.ApplicationRunner;
import com.gong.io.kafkainfra.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.IntStream;

@SpringBootApplication
@RestController(value = "/user")
@EnableBinding(Source.class)
@EnableSwagger2
public class SpringCloudBatchUserProducer {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudBatchUserProducer.class);

    private final MessageChannel output;

    public SpringCloudBatchUserProducer(MessageChannel output) {
        this.output = output;
    }

    public static void main(String[] args) {
        ApplicationRunner.runWithProfile(SpringCloudBatchUserProducer.class, "batch", 9998, args);
    }

    @PostMapping(value = "/publish-batch")
    public void sendBatchMessageToKafkaTopic(@RequestParam("user-prefix") String userPrefix,
                                             @RequestParam("initial-age") Integer initialAge,
                                             @RequestParam("amount") Integer amount) {
        IntStream.range(0, amount)
                .mapToObj(num -> new User(userPrefix, initialAge + num))
                .forEach(this::sendUser);
    }

    private void sendUser(User user) {
        output.send(MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.MESSAGE_KEY, user.getName())
                .build());
        logger.info("Published user {}", user);
    }
}
