package app.sport.sw.dto.club;

import app.sport.sw.enums.Authority;
import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class DefaultClubInfo {

    private long id;
    private String image;
    private String title;
    private String intro;
    private SportType sport;
    private Region region;
    private int personCount;
    private int maxPerson;
    private Authority authority;
}
