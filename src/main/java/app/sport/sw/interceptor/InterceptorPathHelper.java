package app.sport.sw.interceptor;

import app.sport.sw.exception.club.ClubException;
import app.sport.sw.response.ClubError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class InterceptorPathHelper {

    public long getClubId(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/club/", "");
        int index = replace.indexOf("/");
        if (index != -1) replace = replace.substring(0, index);

        try {
            return Long.parseLong(replace);
        } catch (NumberFormatException e) {
            throw new ClubException(ClubError.CLUB_NOT_EXISTS);
        }
    }

}
