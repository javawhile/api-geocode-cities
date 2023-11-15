package da.indian.cities.geo.location.service;

import da.indian.cities.geo.location.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class DataService {

    @Autowired
    @Qualifier("stateMap")
    private Map<String, String> stateMap;

    @Autowired
    @Qualifier("cityMap")
    private Map<String, Set<City>> cityMap;

    public Set<City> searchCityByNameContains(String nameToSearch) {
        if(nameToSearch == null || nameToSearch.isEmpty()) {
            return Collections.emptySet();
        }

        Set<City> cities = new HashSet<>();

        for(String cityName: cityMap.keySet()) {
            if(cityName != null && !cityName.isEmpty() && cityName.toLowerCase().contains(nameToSearch.toLowerCase())) {
                cities.addAll(cityMap.getOrDefault(cityName, new HashSet<>()));
            }
        }

        for(City city: cities) {
            if(city != null && city.getState() != null && !city.getState().isEmpty()) {
                for(String stateCode: stateMap.keySet()) {
                    if(stateCode != null && !stateCode.isEmpty() && stateCode.equalsIgnoreCase(city.getState())) {
                        city.setState(stateMap.getOrDefault(stateCode, city.getState()));
                    }
                }
            }
        }

        return cities;
    }

}
