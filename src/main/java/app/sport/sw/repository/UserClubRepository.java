package app.sport.sw.repository;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.club.ClubListView;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.jparepository.JpaUserClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<UserClub> findByClubIdAndUserId(long clubId, CustomUserDetails userDetails) {
        if (userDetails == null) return Optional.empty();
        long userId = userDetails.getUser().getId();
        return jpaUserClubRepository.findByClub_IdAndUser_Id(clubId, userId);
    }

    public List<UserClub> findByUserId(long userId) {
        return jpaUserClubRepository.findByUser_Id(userId);
    }

    public List<UserClub> findByClubId(long clubId) {
        return jpaUserClubRepository.findByClub_Id(clubId);
    }
}
