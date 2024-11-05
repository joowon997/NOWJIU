package com.nowjoo.nowjiu.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.board.domain.Board;
import com.nowjoo.nowjiu.board.dto.BoardListDto;
import com.nowjoo.nowjiu.board.repository.BoardRespository;
import com.nowjoo.nowjiu.common.FileManager;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class BoardService {

	private BoardRespository boardRespository;
	private UserService userService;
	
	public BoardService(
			BoardRespository boardRespository
			, UserService userService) {
		this.boardRespository = boardRespository;
		this.userService = userService;
	}

	// 게시물 삭제
		public boolean deletePost(int id){
			Optional<Board> optionalBoard = boardRespository.findById(id);
		
			Board board = optionalBoard.orElse(null);
			
			if(board != null) {
				FileManager.removeFile(board.getImage());
				boardRespository.delete(board);
				return true;
			}else {
				return false;
			}
		}
	
	// 게시물 조회
		public List<BoardListDto> getBoardList(){
			List<Board> boardList = boardRespository.findAll();
			
			List<BoardListDto> boardListDtos = new ArrayList<>();
			for(Board board : boardList) {
				int userId = board.getUserId();
				
				User user = userService.getOneUser(userId);
				
				BoardListDto boardListDto = BoardListDto.builder()
														.boardId(board.getId())
														.userId(userId)
														.title(board.getTitle())
														.userName(user.getName())
														.boardCreate(board.getCreatedAt())
														.build();
				boardListDtos.add(boardListDto);
			}	
			
			
			return boardListDtos;
		}
	
		// 게시글조회
		public Board getBoard(int id){
			Optional<Board> optionalBoard = boardRespository.findById(id);
		
			Board board = optionalBoard.orElse(null);
			
			return board;
		}
		
		// 메모 추가
		public Board addBoard(int userId, String title, String contents, MultipartFile file){
			String urlPath = FileManager.saveFile(userId, file);
			
			Board board = Board.builder()
						.userId(userId)
						.title(title)
						.contents(contents)
						.image(urlPath)
						.build();
			
			return boardRespository.save(board);
		}
	
}
