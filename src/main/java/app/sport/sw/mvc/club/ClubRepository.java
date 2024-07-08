package app.sport.sw.mvc.club;

import app.sport.sw.domain.group.Club;
import app.sport.sw.dto.group.DefaultGroupInfo;

import java.util.Optional;

public interface ClubRepository {

    Optional<Club> findById(long clubId);

}
