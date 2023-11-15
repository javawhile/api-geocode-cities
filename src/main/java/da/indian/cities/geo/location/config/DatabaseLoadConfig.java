package da.indian.cities.geo.location.config;

import da.indian.cities.geo.location.db.Database;
import da.indian.cities.geo.location.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
public class DatabaseLoadConfig {

    @Autowired
    private Database database;

    @Bean
    @Qualifier("cityMap")
    public Map<String, Set<City>> loadCityData() {
        return database.fetchCityMap();
    }

    @Bean
    @Qualifier("stateMap")
    public Map<String, String > loadStateData() {
        return database.fetchStateMap();
    }

}
