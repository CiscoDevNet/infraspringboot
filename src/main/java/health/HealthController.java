package health;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import models.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class HealthController {

    private static final Logger log = Logger.getLogger(HealthController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/health", produces = "application/json")
    public ResponseEntity<HealthResponse> health() {

        log.info("Health endpoint triggered");

        HealthResponse response = new HealthResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
