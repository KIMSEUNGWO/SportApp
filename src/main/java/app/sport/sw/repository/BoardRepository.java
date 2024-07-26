package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.enums.group.BoardType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepository {
    void save(Board saveBoard);
    Board findById(long boardId);

    List<Board> findAllByClubIdAndBoardType(long clubId, BoardType boardType, Pageable pageable);

    void delete(Board board);

}
