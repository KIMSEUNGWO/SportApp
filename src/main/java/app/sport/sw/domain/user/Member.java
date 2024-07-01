package app.sport.sw.domain.user;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.ClubJoinRequest;
import app.sport.sw.domain.group.UserClub;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    public long id;

    @Column(unique = true)
    private String email;
    private String nickName;
    @Embedded
    private MemberInfo memberInfo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();


}
