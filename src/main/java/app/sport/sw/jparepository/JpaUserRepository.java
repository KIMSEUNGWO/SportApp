package app.sport.sw.jparepository;

import app.sport.sw.domain.user.User;
import app.sport.sw.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserSocial_SocialIdAndUserSocial_Provider(String socialId, SocialProvider provider);
    Optional<User> findByUserSocial_RefreshToken(String refreshToken);
}
