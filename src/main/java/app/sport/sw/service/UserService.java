package app.sport.sw.service;

import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.jparepository.JpaUserRepository;
import app.sport.sw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    public ResponseProfile getUserProfile(String accessToken) {
        return jpaUserRepository.findByUserSocial_AccessToken(accessToken)
            .map(user -> ResponseProfile.builder()
                .image(user.getImage())
                .name(user.getNickName())
                .sex(user.getUserInfo().getSex())
                .birth(user.getUserInfo().getBirthDate())
                .groupCount(user.getUserClubList().size())
                .inviteCount(0)
                .likeCount(0)
                .build()
        ).orElse(new ResponseProfile());
    }

}
