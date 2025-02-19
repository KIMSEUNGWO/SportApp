package app.sport.sw.dto.club;

import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ClubListView {

    private long id;
    private String thumbnail;
    private String title;
    private String intro;
    private SportType sport;
    private Region region;
    private int personCount;
    private LocalDateTime createDate;
}
