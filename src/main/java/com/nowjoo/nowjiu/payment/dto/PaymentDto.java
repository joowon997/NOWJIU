package com.nowjoo.nowjiu.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class PaymentDto {

	private String merchantUid;
	private String impUid;
}
