package com.rahnema.accounting.service;

import com.rahnema.accounting.domain.AccountOtp;
import com.rahnema.accounting.domain.AccountTransactionRef;
import com.rahnema.accounting.exception.InvalidException;

public interface TreasuryService {

	AccountTransactionRef inc(String tel, Integer amount) throws InvalidException;
	AccountOtp decReq(String tel);
	AccountTransactionRef decSub(String tel, Integer opt, Integer amount) throws InvalidException;
}
