package com.cisco.devnet.example.configs;

import com.cisco.devnet.infracheck.InfraCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public InfraCheck infraCheck() {
        return new InfraCheck();
    }
}