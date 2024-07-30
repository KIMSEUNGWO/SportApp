package app.sport.sw.wrappers;

import app.sport.sw.domain.meeting.Meeting;
import app.sport.sw.dto.meeting.ResponseMeetingView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingWrapper {

    private final UserSimpWrapper userSimpWrapper;

    public ResponseMeetingView meetingListViewWrap(Meeting meeting) {
        return ResponseMeetingView.builder()
            .originalImage(meeting.getMeetingImage().getOriginalName())
            .thumbnailImage(meeting.getMeetingImage().getThumbnailName())
            .id(meeting.getId())
            .title(meeting.getTitle())
            .description(meeting.getDescription())
            .meetingDate(meeting.getMeetingDate())
            .user(userSimpWrapper.userSimpWrap(meeting.getUser()))
            .joinUsers(meeting.getMeetingUserList()
                .stream()
                .map(meetingUser -> userSimpWrapper.userSimpWrap(meetingUser.getUser()))
                .toList()
            )
            .build();
    }

}
