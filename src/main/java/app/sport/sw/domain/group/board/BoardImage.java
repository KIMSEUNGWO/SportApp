package app.sport.sw.domain.group.board;

import app.sport.sw.domain.BaseEntityImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BOARD")
@NoArgsConstructor
@Getter
public class BoardImage extends BaseEntityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_IMAGE_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
