package app.sport.sw.wrappers;

import app.sport.sw.domain.group.Club;
import app.sport.sw.dto.club.ClubListView;
import org.springframework.stereotype.Component;

@Component
public class ClubToClubListViewWrapper {

    public static ClubListView wrapper(Club club) {
        return ClubListView.builder()
            .id(club.getId())
            .thumbnail(club.getClubImage().getThumbnailName())
            .title(club.getTitle())
            .intro(club.getIntro())
            .sport(club.getSportType())
            .region(club.getClubRegion().getRegion())
            .createDate(club.getCreateDate())
            .personCount(club.getPersonCount())
            .build();
    }
}
