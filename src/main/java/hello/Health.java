package hello;

import com.cisco.devnet.infracheck.InfraCheck;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.logging.Logger;

public class Health {

    private static final Logger log = Logger.getLogger(Health.class.getName());

    public HttpResponse<JsonNode> getHealth() {

        String healthStatus;
        HttpResponse<JsonNode> healthObject;
        InfraCheck health = new InfraCheck();

        try {

            log.info("Starting healthcheck process");
            String token = health.getTicket();

            healthObject = health.pathCheck(token);

            healthStatus = healthObject
                    .getBody()
                    .getObject()
                    .getJSONObject("response")
                    .getJSONObject("request")
                    .getString("status");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("Returning health check result");

        return healthObject;

    }
}
