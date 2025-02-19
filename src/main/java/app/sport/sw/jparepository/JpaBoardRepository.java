package app.sport.sw.jparepository;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.enums.group.BoardType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByClub_IdAndBoardTypeOrderByIdDesc(long clubId, BoardType boardType, Pageable pageable);

    List<Board> findAllByClub_IdOrderByIdDesc(long clubId, Pageable pageable);
}
