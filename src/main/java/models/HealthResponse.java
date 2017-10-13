package models;

import com.cisco.devnet.infracheck.InfraCheck;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.logging.Logger;

public class HealthResponse {
    private static final Logger log = Logger.getLogger(HealthResponse.class.getName());

    private static String status;
    private static String originalResponse;

    public HealthResponse() {

        HttpResponse<JsonNode> healthObject;
        InfraCheck health = new InfraCheck();

        try {

            log.info("Starting healthcheck process");

            // Logs into server and receives a "ticket" (aka token)
            String ticket = health.getTicket();

            // Uses ticket in header to execute path check
            healthObject = health.pathCheck(ticket);

            this.originalResponse = healthObject.getBody().toString();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("Returning health check result");

        this.status = healthObject.getBody().getObject().getJSONObject("response").getJSONObject("request").getString("status");

    }


    public String getStatus() {
        return status;
    }

    @JsonRawValue
    public String getOriginalResponse() {
        return originalResponse;
    }


//    public HttpResponse<JsonNode> getHealth() {
//
//        HttpResponse<JsonNode> healthObject;
//        InfraCheck health = new InfraCheck();
//
//        try {
//
//            log.info("Starting healthcheck process");
//
//            // Logs into server and receives a "ticket" (aka token)
//            String ticket = health.getTicket();
//
//            // Uses ticket in header to execute path check
//            healthObject = health.pathCheck(ticket);
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        log.info("Returning health check result");
//
//        return healthObject;
//
//    }
}
