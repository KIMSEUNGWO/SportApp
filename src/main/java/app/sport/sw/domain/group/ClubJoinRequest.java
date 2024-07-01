package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.user.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "CLUB_JOIN_REQUEST")
public class ClubJoinRequest extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_JOIN_REQUEST_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Member member;

    private String message;
}
