package app.sport.sw.jparepository;

import app.sport.sw.domain.meeting.MeetingImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMeetingImageRepository extends JpaRepository<MeetingImage, Long> {
}
