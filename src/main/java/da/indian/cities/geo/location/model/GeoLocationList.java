package da.indian.cities.geo.location.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationList {
    private List<GeoLocation> geoLocations;
}
