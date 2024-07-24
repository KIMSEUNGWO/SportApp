package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepository {
    Comment findByParentComment(Long parentCommentId);

    Comment findById(Long commentId);

    void save(Comment saveComment);

    void deleteById(long commentId);

    // 스크롤 시 페이지 네이션 메소드
    List<Comment> findAllByBoardIdScrollPageable(long boardId, Pageable pageable);

    // 댓글 또는 대댓글 생성시 사용할 메소드
    List<Comment> findAllByBoardIdOrderByCommentId(long boardId, long parentCommentId);

    int countByBoardId(long boardId);
}
