package app.sport.sw.repository;

import app.sport.sw.domain.group.Club;

import java.util.Optional;

public interface ClubRepository {

    Optional<Club> findById(long clubId);

}
