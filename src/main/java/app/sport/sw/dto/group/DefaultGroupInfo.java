package app.sport.sw.dto.group;

import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class DefaultGroupInfo {

    private final String image;
    private final String title;
    private final String intro;
    private final SportType sport;
    private final Region region;
    private final int personCount;
}
