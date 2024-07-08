package app.sport.sw.jparepository;

import app.sport.sw.domain.group.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClubRepository extends JpaRepository<Club, Long> {
}
