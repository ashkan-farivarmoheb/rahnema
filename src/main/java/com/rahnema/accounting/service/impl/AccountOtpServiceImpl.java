package com.rahnema.accounting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountOtp;
import com.rahnema.accounting.repository.AccountOtpRepository;
import com.rahnema.accounting.service.AccountOtpService;

@Service
public class AccountOtpServiceImpl implements AccountOtpService{

	private final Logger log = LoggerFactory.getLogger(AccountOtpServiceImpl.class);
	
	private final AccountOtpRepository accountOtpRepository;
	
	public AccountOtpServiceImpl(AccountOtpRepository accountOtpRepository) {
		super();
		this.accountOtpRepository = accountOtpRepository;
	}

	@Override
	public AccountOtp getByAccountAndOtp(Account account, Integer otp) {		
		return accountOtpRepository.findOneByAccountAndOtpAndExpire(account, otp, false);
	}

	@Override
	public AccountOtp save(AccountOtp accountOtp) {
		log.debug("Request to save AccountOtp : {}", accountOtp);
		accountOtp = accountOtpRepository.save(accountOtp);
		return accountOtp;
	}

}
