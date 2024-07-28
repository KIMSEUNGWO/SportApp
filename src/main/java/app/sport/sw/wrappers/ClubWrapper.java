package app.sport.sw.wrappers;

import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.club.ClubListView;
import app.sport.sw.dto.club.DefaultClubInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClubWrapper {

    public static ClubListView clubListViewWrapper(Club club) {
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

    public DefaultClubInfo defaultClubInfoWrap(Club club, Optional<UserClub> findUserClub) {
        return DefaultClubInfo.builder()
            .id(club.getId())
            .image(club.getClubImage().getStoreName())
            .thumbnail(club.getClubImage().getThumbnailName())
            .title(club.getTitle())
            .intro(club.getIntro())
            .sport(club.getSportType())
            .region(club.getClubRegion().getRegion())
            .personCount(club.getPersonCount())
            .maxPerson(club.getLimitPerson())
            .createDate(club.getCreateDate())
            .authority(findUserClub.map(UserClub::getAuthority).orElse(null))
            .build();
    }
}
