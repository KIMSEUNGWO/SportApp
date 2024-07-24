package app.sport.sw.service;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.comment.RequestEditComment;
import app.sport.sw.dto.comment.ResponseComment;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.repository.CommentRepository;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.wrappers.CommentWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    private final CommentWrapper commentWrapper;

    @Override
    public void createComment(long boardId, long userId, RequestCreateComment createComment) {
        Board board = boardRepository.findById(boardId);
        User user = userRepository.findByUserId(userId);
        Comment parentComment = commentRepository.findByParentComment(createComment.getParentId());

        Comment saveComment = Comment.builder()
            .board(board)
            .user(user)
            .parentComment(parentComment)
            .comment(createComment.getComment())
            .build();
        commentRepository.save(saveComment);
    }

    @Override
    public List<ResponseComment> findByBoardId(long boardId) {
        return boardRepository.findById(boardId)
            .getRootComments()
            .stream()
            .map(commentWrapper::commentWrap)
            .toList();
    }

    @Override
    public void editComment(long commentId, RequestEditComment editComment) {
        Comment comment = commentRepository.findById(commentId);
        comment.setComment(editComment.getComment());
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
