package springproject.board.dto;

import lombok.*;

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

}
