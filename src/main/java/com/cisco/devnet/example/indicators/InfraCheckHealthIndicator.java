package com.cisco.devnet.example.indicators;

import com.cisco.devnet.example.models.HealthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InfraCheckHealthIndicator implements HealthIndicator {

    @Value("${apicem.traceId}")
    private String traceId;

    private static final Logger LOGGER = LoggerFactory.getLogger(InfraCheckHealthIndicator.class);

    @Override
    public Health health() {

        LOGGER.info(String.format("TraceID is equal to %s", traceId));

        HealthResponse response = new HealthResponse();

        if (response.getStatus().equals("FAILED")) {
            return Health.down().withDetail("Error Code", response.getOriginalResponse()).build();
        }
        return Health.up().build();
    }

}