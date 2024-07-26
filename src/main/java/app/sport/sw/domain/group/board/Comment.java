package app.sport.sw.domain.group.board;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "COMMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Setter
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_COMMENT_ID")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replyComments = new ArrayList<>();

    private LocalDateTime updateDate;

    public boolean isUpdate() {
        return updateDate != null;
    }

    public void editComment(String comment) {
        this.comment = comment;
        updateDate = LocalDateTime.now();
    }
}
