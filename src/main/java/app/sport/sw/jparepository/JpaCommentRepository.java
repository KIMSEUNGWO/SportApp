package app.sport.sw.jparepository;

import app.sport.sw.domain.group.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
