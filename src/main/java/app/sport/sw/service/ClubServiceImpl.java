package app.sport.sw.service;

import app.sport.sw.component.authority.AuthorityUserChecker;
import app.sport.sw.component.file.FileService;
import app.sport.sw.component.file.FileType;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.ClubImage;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.region.ClubRegion;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.ClubEditRequest;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.club.RecentlyViewClub;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.ClubGrade;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final AuthorityUserChecker authorityUserChecker;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final FileService fileService;

    @Transactional(readOnly = true)
    public DefaultClubInfo getClubData(long clubId, CustomUserDetails userDetails) {
        Club club = findByClubId(clubId);

        Optional<UserClub> findUserClub = userClubRepository.findByClubIdAndUserId(clubId, userDetails);

        return DefaultClubInfo.builder()
            .image(club.getClubImage().getStoreName())
            .title(club.getTitle())
            .intro(club.getIntro())
            .sport(club.getSportType())
            .region(club.getClubRegion().getRegion())
            .personCount(club.getPersonCount())
            .authority(findUserClub.map(UserClub::getAuthority).orElse(null))
            .build();
    }

    @Override
    public synchronized long createClub(CustomUserDetails userDetails, ClubCreateRequest createRequest) {
        long userId = userDetails.getUser().getId();
        authorityUserChecker.validMaxJoinCount(userDetails);
        authorityUserChecker.validLimitPersonCount(userDetails, createRequest.getLimitPerson());

        User user = userRepository.findByUserId(userId);

        ClubRegion clubRegion = new ClubRegion(createRequest.getRegion());

        Club saveClub = Club.builder()
            .title(createRequest.getTitle())
            .intro(createRequest.getIntro())
            .sportType(createRequest.getSportType())
            .owner(user)
            .limitPerson(createRequest.getLimitPerson())
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

    @Override
    public void editClub(long clubId, ClubEditRequest clubEditRequest) {
        Club club = findByClubId(clubId);

        if (clubEditRequest.getImage() != null) {
            fileService.editImage(clubEditRequest.getImage(), club.getClubImage(), FileType.CLUB_IMAGE);
        }
        if (clubEditRequest.getTitle() != null) {
            club.setTitle(clubEditRequest.getTitle());
        }
        if (clubEditRequest.getIntro() != null) {
            club.setIntro(clubEditRequest.getIntro());
        }
        if (clubEditRequest.getSportType() != null) {
            club.setSportType(clubEditRequest.getSportType());
        }
        if (clubEditRequest.getRegion() != null) {
            club.getClubRegion().setRegion(clubEditRequest.getRegion());
        }
        if (clubEditRequest.getLimitPerson() != null) {
            club.setLimitPerson(clubEditRequest.getLimitPerson());
        }


    }

    @Override
    public synchronized void joinClub(long clubId, CustomUserDetails userDetails) {
        Club club = findByClubId(clubId);
        User user = userRepository.findByUserId(userDetails.getUser().getId());

        // 방이 꽉찬 경우
        if (club.isFull()) throw new ClubException(ClubError.CLUB_JOIN_FULL);

        // 이미 참여중인 그룹인 경우
        boolean exists = userClubRepository.existsByClubIdAndUserId(clubId, userDetails);
        if (exists) throw new ClubException(ClubError.CLUB_ALREADY_JOINED);

        // 참여할 수 있는 그룹의 수를 초과한 경우
        authorityUserChecker.validMaxJoinCount(userDetails);


        UserClub saveUserClub = UserClub.builder()
            .club(club)
            .user(user)
            .authority(Authority.USER)
            .build();

        userClubRepository.save(saveUserClub);
    }

    private Club findByClubId(long clubId) {
        return clubRepository.findById(clubId);
    }
}
