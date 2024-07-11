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
    private final JpaUserRepository jpaUserRepository;

    public User register(SocialLoginDto loginDto, LineProfile profile) {

        UserSocial userSocial = UserSocial.builder()
            .socialId(profile.getUserId())
            .provider(loginDto.getProvider())
            .build();


        User user = User.builder()
            .nickName(profile.getDisplayName())
            .profile(new Profile())
            .userSocial(userSocial)
            .role(Role.USER)
            .build();

        return userRepository.save(user);
    }

    public void insertExtraData(long userId, RegisterRequest register) {
        jpaUserRepository.findById(userId).ifPresent(user -> {
            user.setNickName(register.getNickname());

            UserInfo userInfo = UserInfo.builder()
                .sex(register.getSex().equals("M") ? 'M' : 'F')
                .intro(register.getIntro())
                .birthDate(register.getBirth())
                .build();

            user.setUserInfo(userInfo);
        });
    }

    public boolean registerClear(long id) {
        Optional<User> findUser = jpaUserRepository.findById(id);
        if (findUser.isEmpty()) return true;

        User user = findUser.get();
        if (user.getNickName() == null ||
            user.getUserInfo() == null ||
            user.getUserInfo().getIntro() == null ||
            user.getUserInfo().getBirthDate() == null
        ) {
            System.out.println("Delete : " + id);
            jpaUserRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
