package app.sport.sw.repository;

import app.sport.sw.domain.user.User;
import app.sport.sw.jparepository.JpaUserClubRepository;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserClubRepository jpaUserClubRepository;


    public Optional<User> findByRefreshToken(String refreshToken) {
        return jpaUserRepository.findByUserSocial_RefreshToken(refreshToken);
    }

    public int countByUserJoin(long userId) {
        return jpaUserClubRepository.countByUser_Id(userId);
    }

    public User findByUserId(long userId) {
        return jpaUserRepository
            .findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
