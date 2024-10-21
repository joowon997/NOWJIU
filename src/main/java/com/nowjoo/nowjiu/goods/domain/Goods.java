package com.nowjoo.nowjiu.goods.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="goods")
@Entity
public class Goods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "brandId")
	private int brandId;
	
	@Column(name = "categoryId")
	private int categoryId;
	
	private String name;
	private int price;
	private String description;
	private String image;
	
	@Column(name = "createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}