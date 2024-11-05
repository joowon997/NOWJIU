package com.nowjoo.nowjiu.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.board.domain.Board;
import com.nowjoo.nowjiu.board.dto.BoardListDto;
import com.nowjoo.nowjiu.board.service.BoardService;

@Controller
@RequestMapping("/nowjiu")
public class BoardController {

	private BoardService boardService;
	
	public BoardController(
			BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/board")
	public String boardList(
			Model model){
		
		List<BoardListDto> boards = boardService.getBoardList();
		
		model.addAttribute("boardList", boards);
		
		return "board/list";
	}

	@GetMapping("/board-create")
	public String boardcreate(){
		return "board/create";
	}

	@GetMapping("/board-detail")
	public String boardDetail(
			@RequestParam("boradId") int id
			, Model model){
		
		Board board = boardService.getBoard(id);
		
		model.addAttribute("board", board);
		
		return "board/detail";
	}

	@GetMapping("/map")
	public String map(){
		return "board/map";
	}
}
