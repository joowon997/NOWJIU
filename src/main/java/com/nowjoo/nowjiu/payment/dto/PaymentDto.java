package com.nowjoo.nowjiu.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentDto {

	private String impUid;
	
	private String merchantUid;
	
}
