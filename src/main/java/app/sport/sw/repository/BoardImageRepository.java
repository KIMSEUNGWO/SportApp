package app.sport.sw.repository;

import app.sport.sw.domain.group.board.BoardImage;

import java.util.List;

public interface BoardImageRepository {
    void saveAll(List<BoardImage> saveImages);

    List<BoardImage> findAllById(List<Long> removeImages);

    void deleteAllById(List<Long> removeImages);
}
