package com.rahnema.accounting.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.repository.AccountRepository;
import com.rahnema.accounting.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	private final AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account save(Account account) {		
		log.debug("Request to save Account : {}", account);
		account = accountRepository.save(account);
		return account;
	}

	@Override
	public List<Account> findAll() {
		log.debug("Request to get all Accounts");
		return accountRepository.findAll().stream()
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public Account findOne(Long id) {
		log.debug("Request to get Account : {}", id);
		Account account = accountRepository.findOne(id);
		return account;
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete Account : {}", id);
		accountRepository.delete(id);
		
	}

	@Override
	public Account getAccountByTel(String tel) {
		return accountRepository.findOneByTel(tel);
	}

}
