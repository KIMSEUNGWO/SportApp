package app.sport.sw.component.authority;

import app.sport.sw.domain.group.Club;
import app.sport.sw.enums.ClubGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorityClubChecker {

    public boolean isPremium(Club club) {
        return club.getGrade() == ClubGrade.VIP;
    }
}
