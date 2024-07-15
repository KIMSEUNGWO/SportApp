package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Board;

public interface BoardRepository {
    void save(Board saveBoard);
    Board findById(long boardId);
}
