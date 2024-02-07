package springproject.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springproject.board.dto.BoardDTO;
import springproject.board.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);

        return "index";
    }

    @GetMapping("")
    public String findAll(Model model) {
        // DB에서 뽑아오기
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boards";
    }

    @GetMapping("/{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
        /*
            해당 게시글의 조회수 1+
            게시글 데이터 가져와서 출력
         */
         boardService.updateHits(boardId);
         BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board", boardDTO);
        return "board";
    }

}
