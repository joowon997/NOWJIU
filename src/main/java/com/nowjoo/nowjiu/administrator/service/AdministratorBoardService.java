package com.nowjoo.nowjiu.administrator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.board.domain.Board;
import com.nowjoo.nowjiu.board.dto.BoardListDto;
import com.nowjoo.nowjiu.board.service.BoardService;

@Service
public class AdministratorBoardService {
	private BoardService boardService;
	
	public AdministratorBoardService (
			BoardService boardService) {
		this.boardService = boardService;
	}
	
	public List<BoardListDto> getBoradList(){
		return boardService.getBoardList();
	}
	
	public Board getBoard(int id) {
		return boardService.getBoard(id);
	}
	
}

