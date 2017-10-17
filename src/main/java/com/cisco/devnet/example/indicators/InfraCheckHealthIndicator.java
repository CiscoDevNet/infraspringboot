package com.cisco.devnet.example.indicators;

import com.cisco.devnet.infracheck.InfraCheck;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InfraCheckHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfraCheckHealthIndicator.class);

    @Value("${apicem.traceId}")
    private String traceId;

    private InfraCheck infraCheck;

    public InfraCheckHealthIndicator(InfraCheck infraCheck) {
        this.infraCheck = infraCheck;
    }

    @Override
    public Health health() {

        LOGGER.info(String.format("TraceID is equal to %s", traceId));

        String ticket = infraCheck.getTicket();
        HttpResponse<JsonNode> response = infraCheck.pathCheck(ticket, traceId);

        String healthStatus = response
                .getBody()
                .getObject()
                .getJSONObject("response")
                .getJSONObject("request")
                .getString("status");

        if (healthStatus.equals("FAILED")) {
            return Health.down().withDetail("Error Code", response.getBody().toString()).build();
        }
        return Health.up().build();
    }

}