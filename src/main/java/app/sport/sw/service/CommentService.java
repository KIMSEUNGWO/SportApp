package app.sport.sw.service;

import app.sport.sw.dto.comment.RequestCreateComment;

public interface CommentService {

    void createComment(long boardId, long userId, RequestCreateComment createComment);
}
