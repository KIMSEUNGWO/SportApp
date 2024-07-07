package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.user.User;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "USER_CLUB")
public class UserClub extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CLUB_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    private Authority authority;
}
