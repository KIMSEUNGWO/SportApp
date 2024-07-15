package app.sport.sw.jparepository;

import app.sport.sw.domain.group.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardImageRepository extends JpaRepository<BoardImage, Long> {
}
