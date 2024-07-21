package app.sport.sw.service;

import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.group.BoardType;

import java.util.List;

public interface BoardService {
    long createBoard(long clubId, CustomUserDetails userDetails, BoardCreateRequest createRequest);

    List<ResponseBoard> getBoardList(long clubId, BoardType boardType);
}
