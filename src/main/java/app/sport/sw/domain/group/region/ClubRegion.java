package app.sport.sw.domain.group.region;

import app.sport.sw.enums.region.Region;
import app.sport.sw.enums.region.ParentRegion;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class ClubRegion {

    @Enumerated(EnumType.STRING)
    private ParentRegion location1;
    @Enumerated(EnumType.STRING)
    private Region region;


}
