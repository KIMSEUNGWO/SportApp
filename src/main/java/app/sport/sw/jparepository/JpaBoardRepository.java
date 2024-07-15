package app.sport.sw.jparepository;

import app.sport.sw.domain.group.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {
}
