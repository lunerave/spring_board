package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import springproject.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying // 업데이트 및 수정 시 필수
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.boardId=:boardId")
    void updateHits(@Param("boardId") Long boardId);
}
