package com.rahnema.accounting.service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountTransactionRef;

public interface AccountTransactionRefService {

	AccountTransactionRef save(AccountTransactionRef accountTransactionRef);
	Integer getSumAmount(Account account);
}
