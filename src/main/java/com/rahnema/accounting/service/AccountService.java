package com.rahnema.accounting.service;

import java.util.List;

import com.rahnema.accounting.domain.Account;

public interface AccountService {

	Account save(Account account);

	List<Account> findAll();

	Account findOne(Long id);

	void delete(Long id);
	
	Account getAccountByTel(String tel);
}
