package app.sport.sw.mvc.user;

import app.sport.sw.domain.user.Profile;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.user.UserInfo;
import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.enums.Role;
import app.sport.sw.enums.SocialProvider;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final JpaUserRepository userRepository;

    public User register(SocialLoginDto loginDto) {

        Profile profile = new Profile();
        UserInfo userInfo = UserInfo.builder()
            .sex('M')
            .birth(LocalDateTime.of(1996, 1, 10, 0, 0))
            .name("김승우")
            .build();

        UserSocial userSocial = UserSocial.builder()
            .socialId(loginDto.getSocialId())
            .provider(SocialProvider.LINE)
            .build();

        User user = User.builder()
            .email("abcd")
            .nickName("킴승우")
            .profile(profile)
            .userSocial(userSocial)
            .userInfo(userInfo)
            .role(Role.USER)
            .build();

        return userRepository.save(user);
    }
}
