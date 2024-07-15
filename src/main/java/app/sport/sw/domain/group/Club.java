package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.group.region.ClubRegion;
import app.sport.sw.enums.ClubGrade;
import app.sport.sw.enums.group.SportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "CLUB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Club extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_ID")
    private long id;

    private String title;
    private String intro;
    @Enumerated(EnumType.STRING)
    private SportType sportType;
    @Embedded
    private ClubRegion clubRegion;
    @Enumerated(EnumType.STRING)
    private ClubGrade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLUB_IMAGE_ID")
    private ClubImage clubImage;

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Board> clubBoardList = new ArrayList<>();


    public int getPersonCount() {
        return userClubList.size();
    }

}
