package springproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springproject.board.dto.BoardDTO;
import springproject.board.entity.BoardEntity;
import springproject.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // save 함수가 entity를 받기 때문에 Dto -> Entity 작업 필수
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long boardId) {
        boardRepository.updateHits(boardId);
    }

    public BoardDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getBoardId());
    }

    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작
        int pageLimit = 3; // 한 페이지에 몇개의 게시글을 보여 줄 것 인지
        // 한 페이지에 3개씩 게시글을 보여주고 정렬 기준은 boardId 기준으로 내림차순 => 최신 게시물이 가장 먼저 나온다
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardId")));


        // id, writer, title, hits, creatdTime 필요
        Page<BoardDTO> boardDTOS = boardEntities.map(board ->
                new BoardDTO(board.getBoardId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }
}
