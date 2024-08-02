package app.sport.sw.service;

import app.sport.sw.component.Constant;
import app.sport.sw.component.authority.AuthorityUserChecker;
import app.sport.sw.component.file.FileService;
import app.sport.sw.component.file.FileType;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.ClubImage;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.region.ClubRegion;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.club.*;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.ClubGrade;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.ClubRepository;
import app.sport.sw.wrappers.ClubWrapper;
import app.sport.sw.wrappers.UserClubWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final FileService fileService;
    private final AuthorityService authorityService;

    private final ClubRepository clubRepository;
    private final AuthorityUserChecker authorityUserChecker;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;

    private final ClubWrapper clubWrapper;
    private final UserClubWrapper userClubWrapper;

    @Transactional(readOnly = true)
    public DefaultClubInfo getClubData(long clubId, CustomUserDetails userDetails) {
        Club club = findByClubId(clubId);
        Optional<UserClub> findUserClub = userClubRepository.findByClubIdAndUserId(clubId, userDetails);
        return clubWrapper.defaultClubInfoWrap(club, findUserClub);
    }


    @Override
    public synchronized long createClub(CustomUserDetails userDetails, ClubCreateRequest createRequest) {
        long userId = userDetails.getUser().getId();
        authorityUserChecker.validMaxJoinCount(userDetails);

        User user = userRepository.findByUserId(userId);

        ClubRegion clubRegion = new ClubRegion(createRequest.getRegion());

        Club saveClub = Club.builder()
            .title(createRequest.getTitle())
            .intro(createRequest.getIntro())
            .sportType(createRequest.getSportType())
            .owner(user)
            .limitPerson(Constant.ClUB_MAX_PERSON_COUNT)
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
    public List<ClubListView> findByClubs(List<Long> clubIds) {
        return clubRepository.findAllByIds(clubIds)
            .stream()
            .map(ClubWrapper::clubListViewWrapper)
            .toList();
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
        if (clubEditRequest.getSportType() != null) {
            club.setSportType(clubEditRequest.getSportType());
        }
        if (clubEditRequest.getRegion() != null) {
            club.getClubRegion().setRegion(clubEditRequest.getRegion());
        }

        club.setIntro(clubEditRequest.getIntro());

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

    @Transactional(readOnly = true)
    @Override
    public List<ClubListView> getMyClubs(long userId) {
        return userClubRepository.findByUserId(userId)
            .stream()
            .map(userClub -> ClubWrapper.clubListViewWrapper(userClub.getClub()))
            .toList();
    }

    @Override
    public List<ResponseClubUser> getClubUsers(long clubId) {
        return userClubRepository.findByClubId(clubId)
            .stream()
            .map(userClubWrapper::clubUserWrap)
            .sorted(new ResponseClubUser.Comparator())
            .toList();
    }

    @Override
    public void exitClub(long clubId, CustomUserDetails userDetails) {
        UserClub userClub = userClubRepository.findByClubIdAndUserId(clubId, userDetails)
            .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER));

        // 모임장인 경우
        // 매니저에게 랜덤으로 위임 -> 없으면 일반회원에게 위임
        // 위임할 회원이 없으면 위임 안함
        authorityService.delegateClubOwner(userClub);

        // 마지막 회원인 경우
        // 모임 삭제
        // 아닌 경우
        // 모임 나가기
        Club club = userClub.getClub();
        if (club.getUserClubList().size() <= 1) {
            clubRepository.delete(club);
        } else {
            club.exit(userClub);
        }
    }

    private Club findByClubId(long clubId) {
        return clubRepository.findById(clubId);
    }
}
