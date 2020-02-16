package com.gong.io.kafkainfra.batchconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableSwagger2WebFlux
public class SpringBootBatchUserConsumer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootBatchUserConsumer.class);
        application.setAdditionalProfiles("batch");
        application.run(args);
    }

}
