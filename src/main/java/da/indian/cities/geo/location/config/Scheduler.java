package da.indian.cities.geo.location.config;

import da.indian.cities.geo.location.model.SecureLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private SecureLine secureLine;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void invokeSelf() {
        if(secureLine == null) {
            logger.info("Self Invoke: {}, secureLine not initialized", System.currentTimeMillis());
            return;
        } else if(secureLine.getSelfUrl() == null || secureLine.getSelfUrl().isEmpty()) {
            logger.info("Self Invoke: {}, selfUrl not initialized", System.currentTimeMillis());
            return;
        }

        String selfUrl = secureLine.getSelfUrl().trim();
        String aliveEndpoint = selfUrl.endsWith("/") ? (selfUrl + "application/alive") : (selfUrl  + "/application/alive");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(aliveEndpoint, String.class);
        logger.info("Self Invoke: {}, status: {}", System.currentTimeMillis(), response.getBody());
    }

}
