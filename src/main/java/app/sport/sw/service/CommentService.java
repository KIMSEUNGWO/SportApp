package app.sport.sw.service;

import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.comment.RequestEditComment;
import app.sport.sw.dto.comment.ResponseComment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    void createComment(long boardId, long userId, RequestCreateComment createComment);

    List<ResponseComment> findByBoardId(long boardId, Pageable pageable, int start);

    void editComment(long commentId, RequestEditComment editComment);

    void deleteComment(long commentId);

    int countByBoardId(long boardId);
}
