package da.indian.cities.geo.location.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {
    private Integer id;
    private String countrycode;
    private double latitude;
    private String latitudedirection;
    private double longitude;
    private String longitudedirection;
    private String timezone;
    private String province;
    private String division;
    private String subdivision;
}
