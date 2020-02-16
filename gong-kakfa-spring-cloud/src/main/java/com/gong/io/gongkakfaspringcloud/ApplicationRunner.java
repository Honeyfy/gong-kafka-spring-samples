package com.gong.io.gongkakfaspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

public final class ApplicationRunner {

    public static ConfigurableApplicationContext run(Class<?> springAppClass, int port, String[] args) {
        return runWithProfile(springAppClass, "defailt", port, args);
    }

    public static ConfigurableApplicationContext runWithProfile(Class<?> springAppClass, String profile, int port,
                                                                String[] args) {
        SpringApplication app = new SpringApplication(springAppClass);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", port));
        app.setAdditionalProfiles(profile);
        return app.run(args);
    }
}
