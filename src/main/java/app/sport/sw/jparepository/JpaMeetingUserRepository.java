package app.sport.sw.jparepository;

import app.sport.sw.domain.meeting.MeetingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMeetingUserRepository extends JpaRepository<MeetingUser, Long> {
    boolean existsByMeeting_IdAndUser_Id(long meetingId, long userId);

    void deleteByMeeting_IdAndUser_Id(long meetingId, long userId);
}
