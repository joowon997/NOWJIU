package com.nowjoo.nowjiu.board.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardListDto {

	private int boardId;
	private int userId;
	
	private String title;
	private String userName;
	
	private LocalDateTime boardCreate;
}
