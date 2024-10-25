package com.nowjoo.nowjiu.payment.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.payment.domain.PrePayment;
import com.nowjoo.nowjiu.payment.dto.PaymentDto;
import com.nowjoo.nowjiu.payment.repository.PrePaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Service
public class PaymentService {
	
	private IamportClient iamportClient;
	private PrePaymentRepository prePaymentRepository;
	
	@Value("${spring.imp.key}")
	private String apiKey;
	@Value("${spring.imp.secret}")
	private String secretKey;
	
	public PaymentService(
		PrePaymentRepository prePaymentRepository) {
		this.iamportClient = new IamportClient(apiKey, secretKey);
		this.prePaymentRepository = prePaymentRepository;
	}
	
	// 임시가격 저장
	public void postPrepare(PrePayment request) throws IamportResponseException, IOException {
        PrepareData prepareData = new PrepareData(request.getMerchantUid(), request.getAmount());
        iamportClient.postPrepare(prepareData);  // 사전 등록 API 

        prePaymentRepository.save(request); // 주문번호와 결제예정 금액 DB 저장
    }
	
	// 결제후 비교
	public Payment validatePayment(PaymentDto request) throws IamportResponseException, IOException {
		Optional<PrePayment> optionalPrePayment = prePaymentRepository.findByMerchantUid(request.getMerchantUid());
		PrePayment prePayment = optionalPrePayment.orElse(null);
        BigDecimal preAmount = prePayment.getAmount(); // DB에 저장된 결제요청 금액 
        
        IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(request.getImpUid());
        BigDecimal paidAmount = iamportResponse.getResponse().getAmount(); // 사용자가 실제 결제한 금액

        if (!preAmount.equals(paidAmount)) {
            CancelData cancelData = cancelPayment(iamportResponse);
            iamportClient.cancelPaymentByImpUid(cancelData);
        }

        return iamportResponse.getResponse();
    }
	
	public CancelData cancelPayment(IamportResponse<Payment> response) {
        return new CancelData(response.getResponse().getImpUid(), true);
    }
}
