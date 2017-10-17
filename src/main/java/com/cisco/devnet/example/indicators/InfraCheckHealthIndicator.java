package com.cisco.devnet.example.indicators;

import com.cisco.devnet.infracheck.InfraCheck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        ObjectMapper mapper = new ObjectMapper();

        if (healthStatus.equals("FAILED")) {

            Result result;
            try {
                result = mapper.readValue(response.getBody().getObject().toString(), Result.class);
            } catch (IOException e) {
                throw new RuntimeException("ERROR");
            }

            return Health.down().withDetail("Error Code", result).build();
        }
        return Health.up().build();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        @JsonProperty("response")
        private Response response;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Response {

            @JsonProperty("request")
            private Request request;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Request {

                @JsonProperty("destIP")
                private String destIP;

            }
        }
    }
}