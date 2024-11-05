package com.nowjoo.nowjiu.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.board.domain.Board;
import com.nowjoo.nowjiu.board.service.BoardService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class BoardRestController {
	
	private BoardService boardService;
	
	public BoardRestController(
			BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/board/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value = "imageFile", required = false) MultipartFile file
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Board board = boardService.addBoard(userId, title, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if ( board != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/board/delete")
	public Map<String, String> deletePost(
			@RequestParam("id") int id){
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (boardService.deletePost(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
}
