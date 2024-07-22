package app.sport.sw.component.authority;

import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Role;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.ClubError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorityUserChecker {

    @Value("${vip.user.max.club-count}")
    private int vipJoinClubCount;
    @Value("${user.limit.club-count}")
    private int defaultJoinClubCount;

    private final UserRepository userRepository;

    public void validMaxJoinCount(CustomUserDetails userDetails) {
        boolean isVip = isPremium(userDetails);

        int currentClubCount = userRepository.countByUserJoin(userDetails.getUser().getId());

        int possibleCount = isVip ? vipJoinClubCount : defaultJoinClubCount;
        if (possibleCount <= currentClubCount) {
            throw new ClubException(ClubError.EXCEED_MAX_CLUB);
        }
    }

    private boolean isPremium(UserDetails userDetails) {
        return userDetails
            .getAuthorities()
            .stream()
            .anyMatch(authority -> authority.getAuthority().equals(Role.VIP.name()));
    }
}
