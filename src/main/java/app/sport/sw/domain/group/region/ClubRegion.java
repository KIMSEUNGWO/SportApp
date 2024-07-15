package app.sport.sw.domain.group.region;

import app.sport.sw.enums.region.Region;
import app.sport.sw.enums.region.ParentRegion;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ClubRegion {

    @Enumerated(EnumType.STRING)
    private ParentRegion parentRegion;
    @Enumerated(EnumType.STRING)
    private Region region;

    public ClubRegion(Region region) {
        this.region = region;
        this.parentRegion = region.getParentRegion();
    }

    public void setRegion(Region region) {
        this.region = region;
        this.parentRegion = region.getParentRegion();
    }

}
