package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.user.User;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_CLUB")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserClub extends BaseEntityTime {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CLUB_ID")
    private long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private User user;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Authority authority;

}
