package com.nowjoo.nowjiu.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu")
public class BoradController {

	@GetMapping("/board")
	public String boardList(){
		return "board/list";
	}

	@GetMapping("/board-create")
	public String boardcreate(){
		return "board/create";
	}

	@GetMapping("/map")
	public String map(){
		return "board/map";
	}
}
