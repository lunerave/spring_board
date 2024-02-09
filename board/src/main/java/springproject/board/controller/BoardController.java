package springproject.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String findById(@PathVariable Long boardId, Model model, @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수 1+
            게시글 데이터 가져와서 출력
         */
         boardService.updateHits(boardId);
         BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board";
    }

    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model) {
        BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return "redirect:/board";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "paging";
    }
}
