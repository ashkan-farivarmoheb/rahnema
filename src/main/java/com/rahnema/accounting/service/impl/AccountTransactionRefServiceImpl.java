package com.rahnema.accounting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountTransactionRef;
import com.rahnema.accounting.repository.AccountTransactionRefRepository;
import com.rahnema.accounting.service.AccountTransactionRefService;

@Service
public class AccountTransactionRefServiceImpl implements AccountTransactionRefService{

	private final Logger log = LoggerFactory.getLogger(AccountTransactionRefServiceImpl.class);
	
	private final AccountTransactionRefRepository accountTransactionRefRepository;
	
	public AccountTransactionRefServiceImpl(AccountTransactionRefRepository accountTransactionRefRepository) {
		this.accountTransactionRefRepository = accountTransactionRefRepository;
	}
	
	@Override
	public AccountTransactionRef save(AccountTransactionRef accountTransactionRef) {
		log.debug("Request to save AccountTransactionRef : {}", accountTransactionRef);
		accountTransactionRef = accountTransactionRefRepository.save(accountTransactionRef);
		return accountTransactionRef;
	}

	@Override
	public Integer getSumAmount(Account account) {
		log.debug("Request to save Sum Amount : {}", account);
		return accountTransactionRefRepository.sumAmount(account);
	}

}
