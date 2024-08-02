package app.sport.sw.repository;

import app.sport.sw.domain.meeting.Meeting;
import app.sport.sw.domain.meeting.MeetingUser;
import app.sport.sw.exception.MeetingException;
import app.sport.sw.jparepository.JpaMeetingRepository;
import app.sport.sw.jparepository.JpaMeetingUserRepository;
import app.sport.sw.response.MeetingError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepository {

    private final JpaMeetingRepository jpaMeetingRepository;
    private final JpaMeetingUserRepository jpaMeetingUserRepository;

    @Override
    public List<Meeting> findAllByClubId(long clubId, Pageable pageable) {
        return jpaMeetingRepository.findAllByClub_IdOrderByMeetingDate(clubId, pageable);
    }

    @Override
    public Meeting findById(long meetingId) {
        return jpaMeetingRepository.findById(meetingId)
            .orElseThrow(() -> new MeetingException(MeetingError.MEETING_NOT_EXISTS));
    }

    @Override
    public void saveMeeting(Meeting saveMeeting) {
        jpaMeetingRepository.save(saveMeeting);
    }

    @Override
    public boolean existsByMeetingIdAndUserId(long meetingId, long userId) {
        return jpaMeetingUserRepository.existsByMeeting_IdAndUser_Id(meetingId, userId);
    }

    @Override
    public void saveMeetingUser(MeetingUser saveMeetingUser) {
        jpaMeetingUserRepository.save(saveMeetingUser);
    }

    @Override
    public void deleteBy(Meeting meeting) {
        jpaMeetingRepository.delete(meeting);
    }

    @Override
    public void deleteByMeetingUser_MeetingIdAndUserId(long meetingId, long userId) {
        jpaMeetingUserRepository.deleteByMeeting_IdAndUser_Id(meetingId, userId);
    }
}
