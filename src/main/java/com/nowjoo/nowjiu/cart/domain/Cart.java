package com.nowjoo.nowjiu.cart.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowjoo.nowjiu.administrator.domain.Administrator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "`cart`")
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "goodsId")
	private int goodsId;
	
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
}
