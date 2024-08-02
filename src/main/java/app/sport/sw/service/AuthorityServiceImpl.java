package app.sport.sw.service;

import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    @Override
    public void delegateClubOwner(UserClub userClub) {
        if (userClub.getAuthority() != Authority.OWNER) return;

        Club club = userClub.getClub();
        List<UserClub> userClubList = club.getUserClubList();
        // 남은 사람이 혼자 이거나 없는(예외방지) 경우
        if (userClubList.size() <= 1) return;

        // 자기 자신과 OWNER 가 아닌 회원 목록
        // 정렬 순서 모임장(없음) -> 매니저 -> 일반회원
        List<UserClub> delegateCandidate = userClubList.stream()
            .filter(userClub1 ->
                userClub1.getId() != userClub.getId()
                    && userClub1.getAuthority() != Authority.OWNER
            ).sorted(Comparator.comparingInt(o -> o.getAuthority().getOrderBy()))
            .toList();

        UserClub nextOwnerUser = delegateCandidate.get(0);

        // 모임장 -> 유저로
        // 유저 -> 모임장 으로 변경
        userClub.setAuthority(Authority.USER);
        nextOwnerUser.setAuthority(Authority.OWNER);
    }
}
