package com.cisco.devnet.example.health;

import com.cisco.devnet.example.config.AppConfig;
import com.cisco.devnet.infracheck.InfraCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// Added componentscan when doing configuration
// https://stackoverflow.com/questions/40384056/consider-defining-a-bean-of-type-package-in-your-configuration-spring-boot#comment73086009_40388609

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public InfraCheck infraCheck() {
        return new InfraCheck();
    }

    @Bean
    public AppConfig appConfig() {
        return new AppConfig();
    }
}