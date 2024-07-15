package app.sport.sw.repository;

import app.sport.sw.domain.group.Club;
import app.sport.sw.jparepository.JpaClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryDB implements ClubRepository {

    private final JpaClubRepository jpaClubRepository;

    @Override
    public Optional<Club> findById(long clubId) {
        return jpaClubRepository.findById(clubId);
    }

    @Override
    public void save(Club saveClub) {
        jpaClubRepository.save(saveClub);
    }

    @Override
    public List<Club> findAllByIds(List<Long> clubIds) {
        return jpaClubRepository.findAllById(clubIds);
    }


}
