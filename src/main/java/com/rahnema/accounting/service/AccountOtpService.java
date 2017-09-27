package com.rahnema.accounting.service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountOtp;

public interface AccountOtpService {

	AccountOtp getByAccountAndOtp(Account account, Integer otp);
	AccountOtp save(AccountOtp accountOtp);
}
