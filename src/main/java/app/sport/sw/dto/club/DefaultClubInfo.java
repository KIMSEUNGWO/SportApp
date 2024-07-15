package app.sport.sw.dto.club;

import app.sport.sw.enums.Authority;
import app.sport.sw.enums.Role;
import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class DefaultClubInfo {

    private String image;
    private String title;
    private String intro;
    private SportType sport;
    private Region region;
    private int personCount;
    private boolean isInclude;
    private Authority authority;
}
