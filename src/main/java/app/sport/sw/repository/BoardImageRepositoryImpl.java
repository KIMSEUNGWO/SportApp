package app.sport.sw.repository;

import app.sport.sw.domain.group.board.BoardImage;
import app.sport.sw.jparepository.JpaBoardImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardImageRepositoryImpl implements BoardImageRepository {

    private final JpaBoardImageRepository jpaBoardImageRepository;

    @Override
    public void saveAll(List<BoardImage> saveImages) {
        jpaBoardImageRepository.saveAll(saveImages);
    }

    @Override
    public List<BoardImage> findAllById(List<Long> removeImages) {
        return jpaBoardImageRepository.findAllById(removeImages);
    }

    @Override
    public void deleteAllById(List<Long> removeImages) {
        jpaBoardImageRepository.deleteAllById(removeImages);
    }
}
