package com.nowjoo.nowjiu.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.payment.domain.PrePayment;

public interface PrePaymentRepository extends JpaRepository<PrePayment, Integer>{

	public Optional<PrePayment> findByMerchantUid(String merchantUid);
}
