package app.sport.sw.service;

import app.sport.sw.dto.meeting.RequestCreateMeeting;
import app.sport.sw.dto.meeting.RequestEditMeeting;
import app.sport.sw.dto.meeting.ResponseMeetingView;

import java.util.List;

public interface MeetingService {

    List<ResponseMeetingView> findAllByClubIdAsMeetingView(long clubId);

    ResponseMeetingView findByMeetingIdAsMeetingView(long meetingId);

    void createMeeting(RequestCreateMeeting createMeeting, long clubId, long userId);

    void joinMeeting(long meetingId, long userId);

    void editMeeting(long meetingId, RequestEditMeeting editMeeting);

    void deleteMeeting(long meetingId);

    void exitMeeting(long meetingId, long userId);
}
