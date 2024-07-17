package app.sport.sw.service;

import app.sport.sw.api.LineProfile;
import app.sport.sw.domain.user.Profile;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.user.UserInfo;
import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.dto.user.RegisterRequest;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.enums.Role;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final JpaUserRepository userRepository;

    public User register(RegisterRequest register, LineProfile profile) {

        UserSocial userSocial = UserSocial.builder()
            .socialId(profile.getUserId())
            .provider(register.getProvider())
            .build();

        UserInfo userInfo = UserInfo.builder()
            .sex(register.getSex().equals("M") ? 'M' : 'F')
            .intro(register.getIntro())
            .birthDate(register.getBirth())
            .build();

        User user = User.builder()
            .nickName(register.getNickname())
            .profile(new Profile())
            .userInfo(userInfo)
            .userSocial(userSocial)
            .role(Role.USER)
            .build();

        return userRepository.save(user);
    }

}
