package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springproject.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
