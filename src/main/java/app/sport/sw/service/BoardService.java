package app.sport.sw.service;

import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.user.CustomUserDetails;

public interface BoardService {
    void createBoard(long clubId, CustomUserDetails userDetails, BoardCreateRequest createRequest);
}
