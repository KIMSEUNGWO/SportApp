package app.sport.sw.domain.user;

import app.sport.sw.domain.BaseEntityImage;
import jakarta.persistence.*;

@Entity
@Table(name = "PROFILE")
public class Profile extends BaseEntityImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private long id;

}
