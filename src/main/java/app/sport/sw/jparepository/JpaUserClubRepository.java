package app.sport.sw.jparepository;

import app.sport.sw.domain.group.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserClubRepository extends JpaRepository<UserClub, Long> {

    boolean existsByUser_Id(Long userId);
}
