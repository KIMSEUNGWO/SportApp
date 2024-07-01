package app.sport.sw.domain.group.region;

import app.sport.sw.enums.region.Location1;
import app.sport.sw.enums.region.Location2;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class Region {

    @Enumerated(EnumType.STRING)
    private Location1 location1;
    @Enumerated(EnumType.STRING)
    private Location2 location2;


}
