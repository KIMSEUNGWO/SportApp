package app.sport.sw.repository;

import app.sport.sw.domain.user.User;
import app.sport.sw.enums.SocialProvider;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SocialRepository {

    private final JpaUserRepository jpaUserRepository;

    public Optional<User> findBySocialIdAndProvider(String socialId, SocialProvider provider) {
        return jpaUserRepository.findByUserSocial_SocialIdAndUserSocial_Provider(socialId, provider);
    }

    public Optional<User> findByRefreshToken(String refreshToken) {
        return jpaUserRepository.findByUserSocial_RefreshToken(refreshToken);
    }

}
