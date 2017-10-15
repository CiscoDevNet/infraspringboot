package health;

import com.fasterxml.jackson.annotation.JsonRawValue;
import models.HealthResponse;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InfracheckHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
//        int errorCode = check(); // perform some specific health check

        HealthResponse response = new HealthResponse();

        if (response.getStatus().equals("FAILED")) {
            return Health.down().withDetail("Error Code", response.getOriginalResponse()).build();
        }
        return Health.up().build();
    }

}