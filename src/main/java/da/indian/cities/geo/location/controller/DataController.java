package da.indian.cities.geo.location.controller;

import java.util.Set;
import da.indian.cities.geo.location.model.City;
import da.indian.cities.geo.location.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;

	@GetMapping("/search/{name}")
	public ResponseEntity<Set<City>> search(@PathVariable String name) {
		return ResponseEntity.ok(dataService.searchCityByNameContains(name));
	}

//	@GetMapping("/state/all")
//	public Set<String> stateAll() {
//
//	}
//
//	@GetMapping("/state/{stateCode}")
//	public Set<City> cityAllByState(@PathVariable String stateCode) {
//
//	}
}
