package app.sport.sw.domain.meeting;

import app.sport.sw.domain.BaseEntityImage;
import jakarta.persistence.*;

@Entity
@Table(name = "MEETING_IMAGE")
public class MeetingImage extends BaseEntityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_IMAGE_ID")
    private long id;
}
