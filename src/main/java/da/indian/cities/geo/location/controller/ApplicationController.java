package da.indian.cities.geo.location.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import da.indian.cities.geo.location.model.GeoLocation;
import da.indian.cities.geo.location.model.GeoLocationList;
import da.indian.cities.geo.location.model.SecureLine;
import da.indian.cities.geo.location.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private GeoLocationService geoLocationService;

    @Autowired
    private SecureLine secureLine;

    @GetMapping("/alive")
    public ResponseEntity<String> isAlive() {
        return ResponseEntity.ok("alive");
    }

    @PostMapping("/initialize/{countryIsoCode}")
    public ResponseEntity<String> initialize(
            @PathVariable("countryIsoCode") String countryIsoCode,
            @RequestParam("url") String url,
            @RequestParam("securePassword") String securePassword
    ) {
        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body("url not specified in request parameter");
        } else if (countryIsoCode == null || countryIsoCode.isEmpty()) {
            return ResponseEntity.badRequest().body("countryIsoCode not specified in request path");
        } else if (securePassword == null || securePassword.isEmpty()) {
            return ResponseEntity.badRequest().body("securePassword not specified in request parameter");
        }

        if (secureLine == null || secureLine.getSecurePassword() == null || !secureLine.getSecurePassword().equals(securePassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect securePassword");
        }

        GeoLocationList geoLocationList = getData(url, GeoLocationList.class);
        if (geoLocationList != null && geoLocationList.getGeoLocations() != null && !geoLocationList.getGeoLocations().isEmpty()) {
            List<GeoLocation> geoLocations = geoLocationList.getGeoLocations();
            geoLocationService.putGeoLocationsToMap(countryIsoCode, geoLocations);
            return ResponseEntity.ok(String.format("count=%s data initialized from url=%s", geoLocations.size(), url));
        }

        return ResponseEntity.internalServerError().body(String.format("unable to initialize data from url=%s", url));
    }

    @PostMapping("/selfurl")
    public ResponseEntity<String> initializeSelfUrl(
            @RequestParam("selfUrl") String selfUrl,
            @RequestParam("securePassword") String securePassword
    ) {
        if (selfUrl == null || selfUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("selfUrl not specified in request parameter");
        } else if (securePassword == null || securePassword.isEmpty()) {
            return ResponseEntity.badRequest().body("securePassword not specified in request parameter");
        }

        if (secureLine == null || secureLine.getSecurePassword() == null || !secureLine.getSecurePassword().equals(securePassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect securePassword");
        }

        secureLine.setSelfUrl(selfUrl);
        return ResponseEntity.ok(String.format("selfUrl=%s initialized", selfUrl));
    }

    private <T> T getData(String url, Class<T> classRef) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String data = readRawDataFromUrl(url);
            return mapper.readValue(data, classRef);
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private String readRawDataFromUrl(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        return readStream(inputStream);
    }

    private String readStream(InputStream inputStream) throws Exception {
        if (inputStream != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[2048];
            try {
                Reader Reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                int counter;
                while ((counter = Reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, counter);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        }
        return null;
    }

}
