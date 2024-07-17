package app.sport.sw.repository;

import app.sport.sw.domain.group.Club;
import app.sport.sw.exception.ClubException;
import app.sport.sw.jparepository.JpaClubRepository;
import app.sport.sw.response.ClubError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryDB implements ClubRepository {

    private final JpaClubRepository jpaClubRepository;

    @Override
    public Club findById(long clubId) {
        return jpaClubRepository.findById(clubId).orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_EXISTS));
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
