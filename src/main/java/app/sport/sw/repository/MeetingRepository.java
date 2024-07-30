package app.sport.sw.repository;

import app.sport.sw.domain.meeting.Meeting;
import app.sport.sw.domain.meeting.MeetingUser;

import java.util.List;

public interface MeetingRepository {

    List<Meeting> findAllByClubId(long clubId);

    Meeting findById(long meetingId);

    void saveMeeting(Meeting saveMeeting);

    boolean existsByMeetingIdAndUserId(long meetingId, long userId);

    void saveMeetingUser(MeetingUser saveMeetingUser);

    void deleteBy(Meeting meeting);

    void deleteByMeetingUser_MeetingIdAndUserId(long meetingId, long userId);
}
