package com.gong.io.kafkainfra.singleconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableSwagger2WebFlux
public class SpringBootSingleUserConsumer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSingleUserConsumer.class, args);
    }
}
