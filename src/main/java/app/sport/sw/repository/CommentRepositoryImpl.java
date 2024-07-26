package app.sport.sw.repository;

import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.exception.CommentException;
import app.sport.sw.jparepository.JpaCommentRepository;
import app.sport.sw.response.CommentError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment findByParentComment(Long parentCommentId) {
        if (parentCommentId == null) return null;
        return findById(parentCommentId);
    }

    @Override
    public Comment findById(Long commentId) {
        return jpaCommentRepository.findById(commentId)
            .orElseThrow(() -> new CommentException(CommentError.NOT_EXISTS_COMMENT));
    }

    @Override
    public void save(Comment saveComment) {
        jpaCommentRepository.save(saveComment);
    }

    @Override
    public void deleteById(long commentId) {
        jpaCommentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> findAllByBoardIdScrollPageable(long boardId, Pageable pageable, boolean reload) {
        return reload
            ? jpaCommentRepository.findAllByBoard_Id(boardId, pageable)
            : jpaCommentRepository.findAllByBoard_Id(boardId);
    }

    // TODO
    @Override
    public List<Comment> findAllByBoardIdOrderByCommentId(long boardId, long parentCommentId) {
        return List.of();
    }

    @Override
    public int countByBoardId(long boardId) {
        return jpaCommentRepository.countByBoard_Id(boardId);
    }


}
