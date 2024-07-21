package app.sport.sw.domain.user;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntityTime {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long id;

    @Getter @Setter
    private String nickName;

    @Getter @Setter
    @Embedded
    private UserInfo userInfo;

    @Getter
    @Embedded
    private UserSocial userSocial;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> clubBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public void setRefreshToken(String accessToken) {
        userSocial.setRefreshToken(accessToken);
    }

    public String getRefreshToken() {
        return userSocial.getRefreshToken();
    }

    public String getImage() {
        return profile.getStoreName();
    }
    public String getThumbnail() {
        return profile.getThumbnailName();
    }

}

