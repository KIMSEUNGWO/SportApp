package app.sport.sw.service;

import app.sport.sw.component.AuthorityUserChecker;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.ClubImage;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.region.ClubRegion;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.club.RecentlyViewClub;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.ClubGrade;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.exception.club.ClubException;
import app.sport.sw.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final AuthorityUserChecker authorityUserChecker;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public DefaultClubInfo getClubData(long clubId, CustomUserDetails userDetails) {
        Club club = findByClubId(clubId);

        return DefaultClubInfo.builder()
            .image(club.getClubImage().getStoreName())
            .title(club.getTitle())
            .intro(club.getIntro())
            .sport(club.getSportType())
            .region(club.getClubRegion().getRegion())
            .personCount(club.getPersonCount())
            .isInclude(userClubRepository.existsByClubIdAndUserId(club.getId(), userDetails))
            .build();
    }

    @Override
    public synchronized long createClub(CustomUserDetails userDetails, ClubCreateRequest createRequest) {
        long userId = userDetails.getUser().getId();
        int currentClubCount = userRepository.countByUserJoin(userId);
        authorityUserChecker.getMaxPersonCount(userDetails, currentClubCount);

        User user = userRepository.findByUserId(userId);

        ClubRegion clubRegion = new ClubRegion(createRequest.getRegion());

        Club saveClub = Club.builder()
            .title(createRequest.getTitle())
            .intro(createRequest.getContent())
            .sportType(createRequest.getSportType())
            .owner(user)
            .clubImage(new ClubImage())
            .clubRegion(clubRegion)
            .grade(ClubGrade.NORMAL)
            .build();

        clubRepository.save(saveClub);

        UserClub saveUserClub = UserClub.builder()
            .club(saveClub)
            .user(user)
            .authority(Authority.OWNER)
            .build();

        userClubRepository.save(saveUserClub);

        return saveClub.getId();
    }

    @Override
    public List<RecentlyViewClub> findByClubs(List<Long> clubIds) {
        List<Club> clubs = clubRepository.findAllByIds(clubIds);
        return clubs.stream().map(club ->
            RecentlyViewClub.builder()
                .thumbnail(club.getClubImage().getThumbnailName())
                .title(club.getTitle())
                .intro(club.getIntro())
                .sportType(club.getSportType())
                .region(club.getClubRegion().getRegion())
                .createDate(club.getCreateDate())
                .personCount(club.getPersonCount())
                .build()
        ).toList();
    }

    private Club findByClubId(long clubId) {
        return clubRepository
            .findById(clubId)
            .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_EXISTS));
    }
}
