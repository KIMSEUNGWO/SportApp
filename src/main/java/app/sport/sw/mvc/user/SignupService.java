package app.sport.sw.mvc.user;

import app.sport.sw.domain.user.Profile;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.enums.Role;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final JpaUserRepository userRepository;

    public User register(SocialLoginDto loginDto) {

        UserSocial userSocial = UserSocial.builder()
            .socialId(loginDto.getSocialId())
            .provider(loginDto.getProvider())
            .accessToken(loginDto.getAccessToken())
            .build();

        User user = User.builder()
            .nickName("사용자")
            .profile(new Profile())
            .userSocial(userSocial)
            .role(Role.USER)
            .build();

        return userRepository.save(user);
    }
}
