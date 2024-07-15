package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.group.board.BoardImage;
import app.sport.sw.jparepository.JpaBoardImageRepository;
import app.sport.sw.jparepository.JpaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final JpaBoardRepository jpaBoardRepository;
    private final JpaBoardImageRepository jpaBoardImageRepository;
    @Override
    public void save(Board saveBoard) {
        jpaBoardRepository.save(saveBoard);
    }

    @Override
    public void saveBoardImageAll(List<BoardImage> saveImages) {
        jpaBoardImageRepository.saveAll(saveImages);
    }
}
