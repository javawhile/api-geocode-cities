package da.indian.cities.geo.location.service;

import da.indian.cities.geo.location.model.GeoLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeoLocationService {

    private final Logger logger = LoggerFactory.getLogger(GeoLocationService.class);

    private Map<String, List<GeoLocation>> geoLocations = null;

    public void putGeoLocationsToMap(String countryIsoCode, List<GeoLocation> geoLocations) {
        if (this.geoLocations == null) {
            this.geoLocations = new HashMap<>();
        }
        this.geoLocations.put(countryIsoCode, geoLocations);
    }

    public Set<GeoLocation> searchBySubdivisionContains(String countryIsoCode, String subdivision) {
        if (subdivision == null
                || subdivision.isEmpty()
                || geoLocations == null
                || geoLocations.isEmpty()
                || !geoLocations.containsKey(countryIsoCode)) {
            return Collections.emptySet();
        }

        Set<GeoLocation> filtered = new HashSet<>();

        for (GeoLocation geoLocation : geoLocations.getOrDefault(countryIsoCode, new ArrayList<>())) {
            if (geoLocation != null
                    && geoLocation.getSubdivision() != null
                    && !geoLocation.getSubdivision().isEmpty()
                    && geoLocation.getSubdivision().toLowerCase().contains(subdivision.toLowerCase())) {
                filtered.add(geoLocation);
            }
        }

        return filtered;
    }

}
