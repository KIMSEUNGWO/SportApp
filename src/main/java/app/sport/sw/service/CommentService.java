package app.sport.sw.service;

import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.comment.RequestEditComment;
import app.sport.sw.dto.comment.ResponseComment;

import java.util.List;

public interface CommentService {

    void createComment(long boardId, long userId, RequestCreateComment createComment);

    List<ResponseComment> findByBoardId(long boardId);

    void editComment(long commentId, RequestEditComment editComment);

    void deleteComment(long commentId);
}
