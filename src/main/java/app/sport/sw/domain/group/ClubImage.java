package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityImage;
import jakarta.persistence.*;

@Entity
@Table(name = "CLUB_IMAGE")
public class ClubImage extends BaseEntityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_IMAGE_ID")
    private long id;

}
