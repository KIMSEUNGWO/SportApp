package app.sport.sw.component.authority;

import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Role;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.ClubError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorityUserChecker {

    @Value("${vip.user.max.club-count}")
    private int vipJoinClubCount;
    @Value("${user.limit.club-count}")
    private int defaultJoinClubCount;

    @Value("${vip.user.max.user-count}")
    private int vipLimitPerson;
    @Value("${user.max.user-count}")
    private int defaultLimitPerson;

    private final UserRepository userRepository;

    public void validMaxJoinCount(CustomUserDetails userDetails) {
        boolean isVip = getAuthority(userDetails);

        int currentClubCount = userRepository.countByUserJoin(userDetails.getUser().getId());

        int possibleCount = isVip ? vipJoinClubCount : defaultJoinClubCount;
        if (possibleCount <= currentClubCount) {
            throw new ClubException(ClubError.EXCEED_MAX_CLUB);
        }
    }

    public void validLimitPersonCount(CustomUserDetails userDetails, int limitPerson) {
        boolean isVip = getAuthority(userDetails);

        int possibleLimitPerson = isVip ? vipLimitPerson : defaultLimitPerson;
        if (possibleLimitPerson < limitPerson) {
            throw new ClubException(ClubError.EXCEED_LIMIT_PERSON);
        }
    }

    private boolean getAuthority(UserDetails userDetails) {
        return userDetails
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .anyMatch(authority -> authority.equals(Role.VIP.name()));
    }
}
