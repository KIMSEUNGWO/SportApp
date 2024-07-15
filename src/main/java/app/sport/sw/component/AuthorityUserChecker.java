package app.sport.sw.component;

import app.sport.sw.enums.Role;
import app.sport.sw.exception.VIPException;
import app.sport.sw.exception.club.ClubException;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.VIPCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorityUserChecker {

    @Value("${vip.user.max.club-count}")
    private int vipJoinClubCount;
    @Value("${user.limit.club-count}")
    private int defaultJoinClubCount;

    public void getMaxPersonCount(UserDetails userDetails, int personCount) {
        boolean isVip = getAuthority(userDetails);

        int possibleCount = isVip ? vipJoinClubCount : defaultJoinClubCount;
        if (possibleCount <= personCount) {
            throw new ClubException(ClubError.EXCEED_MAX_CLUB);
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
