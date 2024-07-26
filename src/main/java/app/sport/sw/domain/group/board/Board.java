package app.sport.sw.domain.group.board;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.board.RequestBoardEdit;
import app.sport.sw.enums.group.BoardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Setter
    private BoardType boardType;
    @Setter
    private String title;
    @Setter @Lob
    private String content;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<BoardImage> boardImages = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public String getMainThumbnail() {
        if (boardImages.isEmpty()) return null;
        return boardImages.get(0).getThumbnailName();
    }

    public void edit(RequestBoardEdit boardEdit) {
        title = boardEdit.getTitle();
        content = boardEdit.getContent();
        boardType = boardEdit.getBoardType();
        updateDate = LocalDateTime.now();
    }

    public boolean isUpdate() {
        return updateDate != null;
    }
}
