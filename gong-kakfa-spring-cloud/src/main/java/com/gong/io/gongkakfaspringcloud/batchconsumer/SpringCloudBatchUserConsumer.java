
package com.gong.io.gongkakfaspringcloud.batchconsumer;

import com.gong.io.gongkakfaspringcloud.ApplicationRunner;
import com.gong.io.kafkainfra.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringCloudBatchUserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudBatchUserConsumer.class);

    public static void main(String[] args) {
        ApplicationRunner.runWithProfile(SpringCloudBatchUserConsumer.class, "batch", 9999, args);
    }

    @StreamListener(Sink.INPUT)
    public void handle(List<User> users) {
        logger.info("Got {} users", users.size());
    }
}

