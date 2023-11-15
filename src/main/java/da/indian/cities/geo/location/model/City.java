package da.indian.cities.geo.location.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class City {
	private String name;
	private String state;
	private String latitude;
	private String longitude;
}
