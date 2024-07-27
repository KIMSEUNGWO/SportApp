package app.sport.sw.dto.club;

import app.sport.sw.dto.common.UserSimp;
import app.sport.sw.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ResponseClubUser {

    private final UserSimp user;
    private final Authority authority;

    public static class Comparator implements java.util.Comparator<ResponseClubUser> {

        @Override
        public int compare(ResponseClubUser o1, ResponseClubUser o2) {
            return o1.getAuthority().getOrderBy() - o2.getAuthority().getOrderBy();
        }
    }

}
