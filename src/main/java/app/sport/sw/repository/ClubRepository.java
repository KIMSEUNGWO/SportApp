package app.sport.sw.repository;

import app.sport.sw.domain.group.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {

    Club findById(long clubId);

    void save(Club saveClub);

    List<Club> findAllByIds(List<Long> clubIds);
}
