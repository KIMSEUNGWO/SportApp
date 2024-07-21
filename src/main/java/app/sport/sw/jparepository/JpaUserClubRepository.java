package app.sport.sw.jparepository;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.club.ClubListView;
import app.sport.sw.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserClubRepository extends JpaRepository<UserClub, Long> {

    boolean existsByClub_IdAndUser_Id(long clubId, long userId);
    int countByUser_Id(long userId);
    boolean existsByClub_IdAndUser_IdAndAuthority(long clubId, long userId, Authority authority);

    Optional<UserClub> findByClub_IdAndUser_Id(long clubId, long userId);

    List<UserClub> findByUser_Id(long userId);
    List<UserClub> findByClub_Id(long clubId);
}
