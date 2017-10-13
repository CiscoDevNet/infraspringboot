package hello;

import com.cisco.devnet.infracheck.InfraCheck;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class HealthController {

    private static final Logger log = Logger.getLogger(Health.class.getName());

    @RequestMapping("/health")
    public void health(HttpServletResponse response) throws IOException{

        log.info("Health endpoint triggered");

        HttpResponse<JsonNode> result = new Health().getHealth();

        if (result.getBody()
                .getObject()
                .getJSONObject("response")
                .getJSONObject("request")
                .getString("status").equals("FAILED")) {

            log.info("Health check failed: Sending response");

            response.sendError(500, result.getBody().toString());
        } else {

            log.info("Health check passed: Sending response");

            response.setStatus(200);

        }
    }

}
