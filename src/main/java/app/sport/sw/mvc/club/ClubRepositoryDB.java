package app.sport.sw.mvc.club;

import app.sport.sw.domain.group.Club;
import app.sport.sw.dto.group.DefaultGroupInfo;
import app.sport.sw.jparepository.JpaClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryDB implements ClubRepository {

    private final JpaClubRepository jpaClubRepository;

    @Override
    public Optional<Club> findById(long clubId) {
        return jpaClubRepository.findById(clubId);
    }
}
