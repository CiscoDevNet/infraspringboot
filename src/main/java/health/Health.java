package health;

import com.cisco.devnet.infracheck.InfraCheck;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.logging.Logger;

public class Health {

    private static final Logger log = Logger.getLogger(Health.class.getName());

    public HttpResponse<JsonNode> getHealth() {

        HttpResponse<JsonNode> healthObject;
        InfraCheck health = new InfraCheck();

        try {

            log.info("Starting healthcheck process");

            // Logs into server and receives a "ticket" (aka token)
            String ticket = health.getTicket();

            // Uses ticket in header to execute path check
            healthObject = health.pathCheck(ticket);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("Returning health check result");

        return healthObject;

    }
}
