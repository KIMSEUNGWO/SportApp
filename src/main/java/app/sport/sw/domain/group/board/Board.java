package app.sport.sw.domain.group.board;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.user.User;
import app.sport.sw.enums.group.BoardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "BOARD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private BoardType boardType;
    private String title;
    @Lob
    private String content;

    @OneToMany(mappedBy = "board")
    private List<BoardImage> boardImages = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
}
