package springproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springproject.board.dto.BoardDTO;
import springproject.board.entity.BoardEntity;
import springproject.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // save 함수가 entity를 받기 때문에 Dto -> Entity 작업 필요
    }
}
