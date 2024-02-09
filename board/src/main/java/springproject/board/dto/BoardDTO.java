package springproject.board.dto;

import lombok.*;
import springproject.board.entity.BoardEntity;

import java.time.LocalDateTime;

// DTO(Data Transfer Object , VO, Bean 비슷
@Getter @Setter
@ToString
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
@NoArgsConstructor // 기본 생성자
public class BoardDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public BoardDTO(Long boardId, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        return boardDTO;
    }

}
