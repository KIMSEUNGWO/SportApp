package app.sport.sw.domain.user;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.ClubJoinRequest;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID") @Getter
    private long id;

    private String nickName;

    @Embedded @Getter
    private UserSocial userSocial;

    @Enumerated(EnumType.STRING) @Getter
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();


    public void setRefreshToken(String refreshToken) {
        userSocial.setRefreshToken(refreshToken);
    }

    public void setAccessToken(String accessToken) {
        userSocial.setAccessToken(accessToken);
    }
}

