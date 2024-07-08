package app.sport.sw.service;

import app.sport.sw.domain.group.Club;
import app.sport.sw.dto.group.DefaultGroupInfo;
import app.sport.sw.dto.group.ResponseDefaultGroupData;
import app.sport.sw.exception.ClubError;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
    public ResponseDefaultGroupData getClubData(long clubId) {

        Club club = findByClubId(clubId);

        DefaultGroupInfo defaultInfo = DefaultGroupInfo.builder()
            .image(club.getClubImage().getStoreName())
            .title(club.getTitle())
            .intro(club.getIntro())
            .sport(club.getSportType())
            .region(club.getClubRegion().getRegion())
            .personCount(club.getPersonCount())
            .build();


        return new ResponseDefaultGroupData(defaultInfo, null);
    }

    private Club findByClubId(long clubId) {
        return clubRepository
            .findById(clubId)
            .orElseThrow(() -> new ClubException(ClubError.NOT_EXISTS));
    }
}
