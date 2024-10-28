package com.nowjoo.nowjiu.order.domain;


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
@Table(name="`order`")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "merchantUid")
	private String merchantUid;
	
	@Column(name = "payMethod")
	private String payMethod;

	@Column(name = "userName")
	private String userName;
	
	private int amount;
	
	private String phone;
	private String address;
	
	private int postcode;
	
//	@Column(name = "createdAt")
//	@CreationTimestamp
//	private LocalDateTime createdAt;
//	
//	@Column(name = "updatedAt")
//	@UpdateTimestamp
//	private LocalDateTime updatedAt;
}
