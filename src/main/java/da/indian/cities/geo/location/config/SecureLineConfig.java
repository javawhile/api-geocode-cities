package da.indian.cities.geo.location.config;

import da.indian.cities.geo.location.model.SecureLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class SecureLineConfig {

    private final Logger logger = LoggerFactory.getLogger(SecureLineConfig.class);

    @Bean
    public SecureLine registerSecurityBean() {
        String randomUUID = UUID.randomUUID().toString();
        logger.info("registerSecurityBean: securePassword = {}", randomUUID);
        SecureLine secureLine = new SecureLine();
        secureLine.setSecurePassword(randomUUID);
        return secureLine;
    }

}
