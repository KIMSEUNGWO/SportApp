package app.sport.sw.jparepository;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserClubRepository extends JpaRepository<UserClub, Long> {

    boolean existsByClub_IdAndUser_Id(long clubId, long userId);
    int countByUser_Id(long userId);
    boolean existsByClub_IdAndUser_IdAndAuthority(long clubId, long userId, Authority authority);
}
