package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Comment;

public interface CommentRepository {
    Comment findByParentComment(Long parentCommentId);

    Comment findById(Long commentId);

    void save(Comment saveComment);

    void deleteById(long commentId);
}
