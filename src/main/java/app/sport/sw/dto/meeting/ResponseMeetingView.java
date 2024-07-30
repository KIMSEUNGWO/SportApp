package app.sport.sw.dto.meeting;

import app.sport.sw.dto.common.UserSimp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseMeetingView {

    private final long id;
    private final String originalImage;
    private final String thumbnailImage;
    private final String title;
    private final String description;
    private final UserSimp user;
    private final LocalDateTime meetingDate;

    private final List<UserSimp> joinUsers;

}
