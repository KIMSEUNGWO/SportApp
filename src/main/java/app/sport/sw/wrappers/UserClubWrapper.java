package app.sport.sw.wrappers;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.club.ResponseClubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClubWrapper {

    private final UserSimpWrapper userSimpWrapper;

    public ResponseClubUser clubUserWrap(UserClub userClub) {
        return ResponseClubUser.builder()
            .user(userSimpWrapper.userSimpWrap(userClub.getUser()))
            .authority(userClub.getAuthority())
            .build();
    }
}
