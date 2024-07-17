package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.user.User;
import app.sport.sw.domain.group.region.ClubRegion;
import app.sport.sw.enums.ClubGrade;
import app.sport.sw.enums.group.SportType;
import app.sport.sw.exception.ClubException;
import app.sport.sw.response.ClubError;
import jakarta.persistence.*;
import lombok.*;

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

    @Setter
    private String title;
    @Setter
    private String intro;
    @Setter
    @Enumerated(EnumType.STRING)
    private SportType sportType;
    @Embedded
    private ClubRegion clubRegion;
    @Enumerated(EnumType.STRING)
    private ClubGrade grade;

    private int limitPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLUB_IMAGE_ID")
    private ClubImage clubImage;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Board> clubBoardList = new ArrayList<>();


    public int getPersonCount() {
        return userClubList.size();
    }

    public void setLimitPerson(int limitPerson) {
        if (getPersonCount() > limitPerson) throw new ClubException(ClubError.EXCEED_LIMIT_PERSON);
        this.limitPerson = limitPerson;
    }

    public boolean isFull() {
        return limitPerson <= getPersonCount();
    }

}
