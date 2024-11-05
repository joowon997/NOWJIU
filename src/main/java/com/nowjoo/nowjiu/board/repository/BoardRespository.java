package com.nowjoo.nowjiu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.board.domain.Board;

public interface BoardRespository extends JpaRepository<Board, Integer> {

}
