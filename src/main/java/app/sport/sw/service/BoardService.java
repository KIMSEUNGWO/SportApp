package app.sport.sw.service;

import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.user.CustomUserDetails;

public interface BoardService {
    long createBoard(long clubId, CustomUserDetails userDetails, BoardCreateRequest createRequest);
}
