package app.sport.sw.jparepository;

import app.sport.sw.domain.group.board.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

    // TODO
    // 현재 댓글 페이지네이션 처리시 문제점
    // 1. 대댓글의 번호가 뒷번호일경우 맨 아래까지 스크롤해야지 앞에 있는 대댓글이 활성화됨.
    //      아래 메소드를 사용하면 이 문제는 해결
    // 2. 하지만 그 다음 문제는 대댓글을 새로운 사용자가 작성한경우,
    //      아래 메소드를 사용하면 페이지 번호가 넘어갔기 때문에 새로운 데이터를 가져오려면 전체 새로고침을 해야됨
    // 따라서 1번과 2번 메소드를 나눌 필요가 있어보임.
//    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.replyComments " +
//        "WHERE c.board.id = :boardId " +
//        "ORDER BY CASE WHEN c.parentComment IS NULL THEN c.id ELSE c.parentComment.id END, c.id")
//    Page<Comment> findAllByBoardIdOrderByScroll(@Param("boardId") long boardId, Pageable pageable);
    List<Comment> findAllByBoard_Id(long boardId, Pageable pageable);
    List<Comment> findAllByBoard_Id(long boardId);

    int countByBoard_Id(long boardId);
}
