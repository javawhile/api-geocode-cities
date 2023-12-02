package da.indian.cities.geo.location.controller;

import da.indian.cities.geo.location.model.GeoLocation;
import da.indian.cities.geo.location.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping("/subdivision/{countryIsoCode}/{name}")
    public ResponseEntity<Set<GeoLocation>> search(@PathVariable("countryIsoCode") String countryIsoCode, @PathVariable String name) {
        return ResponseEntity.ok(geoLocationService.searchBySubdivisionContains(countryIsoCode, name));
    }
}
