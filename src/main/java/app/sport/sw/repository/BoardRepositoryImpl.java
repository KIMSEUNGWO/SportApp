package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.exception.BoardException;
import app.sport.sw.jparepository.JpaBoardRepository;
import app.sport.sw.response.BoardError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final JpaBoardRepository jpaBoardRepository;
    @Override
    public void save(Board saveBoard) {
        jpaBoardRepository.save(saveBoard);
    }

    @Override
    public Board findById(long boardId) {
        return jpaBoardRepository.findById(boardId)
                .orElseThrow(() -> new BoardException(BoardError.BOARD_NOT_EXISTS));
    }

    @Override
    public List<Board> findAllByClubIdAndBoardType(long clubId, BoardType boardType, Pageable pageable) {
        if (boardType == null) return jpaBoardRepository.findAllByClub_IdOrderByIdDesc(clubId, pageable);
        return jpaBoardRepository.findAllByClub_IdAndBoardTypeOrderByIdDesc(clubId, boardType, pageable);
    }

}
