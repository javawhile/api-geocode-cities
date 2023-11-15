package da.indian.cities.geo.location.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/alive")
    public ResponseEntity<String> isAlive() {
        return ResponseEntity.ok("alive");
    }

}
