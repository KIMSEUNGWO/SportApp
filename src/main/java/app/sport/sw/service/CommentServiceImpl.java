package app.sport.sw.service;

import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.repository.CommentRepository;
import app.sport.sw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    @Override
    public void createComment(long boardId, long userId, RequestCreateComment createComment) {
        Board board = boardRepository.findById(boardId);
        User user = userRepository.findByUserId(userId);
        Comment parentComment = commentRepository.findByParentComment(createComment.getParentCommentId());

        Comment saveComment = Comment.builder()
            .board(board)
            .user(user)
            .parentComment(parentComment)
            .comment(createComment.getContent())
            .build();
        commentRepository.save(saveComment);
    }
}
