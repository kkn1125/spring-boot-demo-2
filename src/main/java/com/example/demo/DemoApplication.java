package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "custom")
@SpringBootApplication
public class DemoApplication {

    private static boolean reactive;

    public static void main(String[] args) {
        System.out.println("enabled: " + reactive);
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).web(reactive ? WebApplicationType.REACTIVE : WebApplicationType.SERVLET).run();
    }


}
