package com.gong.io.gongkakfaspringcloud.singleconsumer;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController(value = "/user")
@EnableBinding(Source.class)
@EnableSwagger2
public class SpringCloudSingleUserProducer {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudSingleUserProducer.class);

    private final MessageChannel output;

    public SpringCloudSingleUserProducer(MessageChannel output) {
        this.output = output;
    }

    public static void main(String[] args) {
        ApplicationRunner.run(SpringCloudSingleUserProducer.class, 9998, args);
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String sendMessage(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        final User user = new User(name, age);
        output.send(MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.MESSAGE_KEY, user.getName())
                .build());
        logger.info("Published user {}", user);
        return "user " + user + " sent";
    }
}
