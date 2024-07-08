package app.sport.sw.service;

import app.sport.sw.api.LineProfile;
import app.sport.sw.domain.user.Profile;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.user.UserInfo;
import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.enums.Role;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final JpaUserRepository userRepository;

    public User register(SocialLoginDto loginDto, LineProfile profile) {

        UserSocial userSocial = UserSocial.builder()
            .socialId(profile.getUserId())
            .provider(loginDto.getProvider())
            .accessToken(loginDto.getAccessToken())
            .build();

        UserInfo userInfo = UserInfo.builder()
            .sex('M')
            .birthDate(LocalDate.of(1996, 1, 10))
            .build();

        User user = User.builder()
            .nickName(profile.getDisplayName())
            .profile(new Profile())
            .userSocial(userSocial)
            .userInfo(userInfo)
            .role(Role.USER)
            .build();

        return userRepository.save(user);
    }
}
