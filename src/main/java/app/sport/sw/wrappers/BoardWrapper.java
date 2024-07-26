package app.sport.sw.wrappers;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.board.ResponseBoardDetail;
import app.sport.sw.dto.board.ResponseBoardImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardWrapper {

    public ResponseBoard boardWrap(Board board) {
        return ResponseBoard.builder()
            .boardId(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .boardType(board.getBoardType())
            .thumbnailBoard(board.getMainThumbnail())
            .likeCount(0) // 좋아요 기능 활성화시 변경
            .commentCount(board.getComments().size())
            .createDate(board.getCreateDate())
            .userId(board.getUser().getId())
            .thumbnailUser(board.getUser().getThumbnail())
            .nickname(board.getUser().getNickName())
            .build();
    }

    public ResponseBoardDetail boardDetailWrap(Board board) {
        return ResponseBoardDetail.builder()
            .boardId(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .boardType(board.getBoardType())
            .createDate(board.getCreateDate())
            .isUpdate(board.isUpdate())
            .likeCount(0)
            .userId(board.getUser().getId())
            .thumbnailUser(board.getUser().getThumbnail())
            .nickname(board.getUser().getNickName())
            .images(board.getBoardImages()
                .stream()
                .map(boardImage -> new ResponseBoardImage(boardImage.getId(), boardImage.getStoreName()))
                .toList())
            .build();
    }
}
