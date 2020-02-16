
package com.gong.io.gongkakfaspringcloud.singleconsumer;

import com.gong.io.gongkakfaspringcloud.ApplicationRunner;
import com.gong.io.kafkainfra.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringCloudUserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudUserConsumer.class);

    public static void main(String[] args) {
        ApplicationRunner.run(SpringCloudUserConsumer.class, 9999, args);
    }

    @StreamListener(Sink.INPUT)
    public void handle(User user) {
        logger.info("Received: {}", user);
    }
}

