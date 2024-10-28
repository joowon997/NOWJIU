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

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {
	
	private IamportClient iamportClient;
	private PrePaymentRepository prePaymentRepository;
	
	@Value("${spring.imp.key}")
	private String apiKey;
	@Value("${spring.imp.secret}")
	private String secretKey;
	
	@PostConstruct
    public void init() {
        this.iamportClient = new IamportClient("3186342728772463", "rV9bMFSr8KTEsZSZ8gumwY309WisCfumwpIgNJPBwDku3cHiAKNQsB1tvbgqk3Q3zNbIzS4YIglBVMg2");
    }
	
	public PaymentService(
		PrePaymentRepository prePaymentRepository) {
		this.prePaymentRepository = prePaymentRepository;
	}
	
	// 임시가격 저장
	public void postPrepare(PrePayment request) throws IamportResponseException, IOException {
        PrepareData prepareData = new PrepareData(request.getMerchantUid(), request.getAmount());
        iamportClient.postPrepare(prepareData);  // 사전 등록 API 

        prePaymentRepository.save(request); // 주문번호와 결제예정 금액 DB 저장
    }
	
	// 임시가격 삭제
	public void deletePrepare(String merchantUid) throws IamportResponseException, IOException {
		Optional<PrePayment> optionalPrePayment = prePaymentRepository.findByMerchantUid(merchantUid);
		PrePayment prePayment = optionalPrePayment.orElse(null); // 사전 등록 조회 
		
		if(prePayment != null) {
			prePaymentRepository.delete(prePayment); // 주문번호와 결제예정 금액 DB 삭제
		}
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
