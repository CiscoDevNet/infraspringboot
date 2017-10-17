package com.cisco.devnet.example.health;

import com.cisco.devnet.example.config.AppConfig;
import com.cisco.devnet.example.models.HealthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InfracheckHealthIndicator implements HealthIndicator {

    @Autowired
    private AppConfig appConfig;

    @Override
    public Health health() {
//        int errorCode = check(); // perform some specific health check

        System.out.println("CONFIG");
        System.out.println(appConfig.getTraceId());;

        HealthResponse response = new HealthResponse();

        if (response.getStatus().equals("FAILED")) {
            return Health.down().withDetail("Error Code", response.getOriginalResponse()).build();
        }
        return Health.up().build();
    }

}