package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.enums.group.BoardType;

import java.util.List;

public interface BoardRepository {
    void save(Board saveBoard);
    Board findById(long boardId);

    List<Board> findAllByClubIdAndBoardType(long clubId, BoardType boardType);
}
