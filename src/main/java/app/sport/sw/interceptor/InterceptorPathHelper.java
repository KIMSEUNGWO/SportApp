package app.sport.sw.interceptor;

import app.sport.sw.exception.BoardException;
import app.sport.sw.exception.ClubException;
import app.sport.sw.exception.CommentException;
import app.sport.sw.exception.MeetingException;
import app.sport.sw.response.BoardError;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.CommentError;
import app.sport.sw.response.MeetingError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InterceptorPathHelper {


    private String getDefaultUri(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/public")) {
            requestURI = requestURI.substring("/public".length());
        }
        return requestURI;
    }
    public long getClubId(HttpServletRequest request) {
        String requestURI = getDefaultUri(request);
        String replace = requestURI.replace("/club/", "");
        int index = replace.indexOf("/");
        if (index != -1) replace = replace.substring(0, index);

        try {
            return Long.parseLong(replace);
        } catch (NumberFormatException e) {
            throw new ClubException(ClubError.CLUB_NOT_EXISTS);
        }
    }

    public long getBoardId(HttpServletRequest request) {
        String requestURI = getDefaultUri(request);
        String replace = requestURI
                .replaceAll("/club/\\d+/board/", "");
        int index = replace.indexOf("/");
        if (index != -1) replace = replace.substring(0, index);

        try {
            return Long.parseLong(replace);
        } catch (NumberFormatException e) {
            throw new BoardException(BoardError.BOARD_NOT_EXISTS);
        }
    }

    public long getCommentId(HttpServletRequest request) {
        String requestURI = getDefaultUri(request);
        String replace = requestURI
            .replaceAll("/club/\\d+/board/\\d+/comment/", "");
        int index = replace.indexOf("/");
        if (index != -1) replace = replace.substring(0, index);

        try {
            return Long.parseLong(replace);
        } catch (NumberFormatException e) {
            throw new CommentException(CommentError.NOT_EXISTS_COMMENT);
        }
    }

    public long getMeetingId(HttpServletRequest request) {
        String requestURI = getDefaultUri(request);
        String replace = requestURI
            .replaceAll("/club/\\d+/meeting/", "");
        int index = replace.indexOf("/");
        if (index != -1) replace = replace.substring(0, index);

        try {
            return Long.parseLong(replace);
        } catch (NumberFormatException e) {
            throw new MeetingException(MeetingError.MEETING_NOT_EXISTS);
        }
    }
}
