package app.sport.sw.jparepository;

import app.sport.sw.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByClub_Id(long clubId);
}
