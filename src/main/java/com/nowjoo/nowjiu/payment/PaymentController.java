package com.nowjoo.nowjiu.payment;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.payment.domain.PrePayment;
import com.nowjoo.nowjiu.payment.dto.PaymentDto;
import com.nowjoo.nowjiu.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RestController
@RequestMapping("/api")
public class PaymentController{
	private PaymentService paymentService;
	
	public PaymentController(
			PaymentService paymentService
			) {
		this.paymentService = paymentService;
	}
	
	
	 @PostMapping("/payment/prepare")
	    public void preparePayment(@RequestBody PrePayment request) 
	    								throws IamportResponseException, IOException {
		  paymentService.postPrepare(request);
	    }
	 
	 @PostMapping("/payment/validate")
	 public void preparePayment(@RequestBody PaymentDto request) 
			 throws IamportResponseException, IOException {
		 
		 paymentService.validatePayment(request);
	 }
	
	
}
