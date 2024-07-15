package app.sport.sw.repository;

import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.jparepository.JpaUserClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserClubRepository {

    private final JpaUserClubRepository jpaUserClubRepository;

    public void save(UserClub userClub) {
        jpaUserClubRepository.save(userClub);
    }

    public boolean existsByClubIdAndUserId(long clubId, CustomUserDetails userDetails) {
        if (userDetails == null) return false;
        long userId = userDetails.getUser().getId();
        return jpaUserClubRepository.existsByClub_IdAndUser_Id(clubId, userId);
    }
}
