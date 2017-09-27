package com.rahnema.accounting.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountOtp;
import com.rahnema.accounting.domain.AccountTransactionRef;
import com.rahnema.accounting.exception.InvalidException;
import com.rahnema.accounting.service.AccountOtpService;
import com.rahnema.accounting.service.AccountService;
import com.rahnema.accounting.service.AccountTransactionRefService;
import com.rahnema.accounting.service.TreasuryService;

@Service
public class TreasuryServiceImpl implements TreasuryService{

	private final AccountService accountService;
	private final AccountOtpService accountOtpService;
	private final AccountTransactionRefService accountTransactionRefService;
	
	@Value("${validTime}")
	private Integer validTime;
	
	public TreasuryServiceImpl(AccountService accountService, AccountOtpService accountOtpService,
			AccountTransactionRefService accountTransactionRefService) {
		this.accountService = accountService;
		this.accountOtpService = accountOtpService;
		this.accountTransactionRefService = accountTransactionRefService;
	}

	@Override
	public AccountTransactionRef inc(String tel, Integer amount) throws InvalidException{
		Account account = accountService.getAccountByTel(tel);
		if(account == null)
			throw new InvalidException("Account not Found");
		
		synchronized(account){
			AccountTransactionRef ref = new AccountTransactionRef(account, amount);
			accountTransactionRefService.save(ref);
			return ref;
		}
	}

	@Override
	public AccountOtp decReq(String tel) {
		AccountOtp accountOtp = null;
		Account account = accountService.getAccountByTel(tel);
		if(account != null){
			accountOtp = new AccountOtp(account, randomOtp(), false);
			accountOtpService.save(accountOtp);
		}			
		return accountOtp;
	}

	@Override
	public AccountTransactionRef decSub(String tel, Integer opt, Integer amount) throws InvalidException {
		Account account = accountService.getAccountByTel(tel);
		
		AccountOtp accountOtp = isOtpExists(account, opt);
		if(accountOtp == null)
			throw new InvalidException("Otp Failed"); 
		
		synchronized (account) {
			if(amount > accountTransactionRefService.getSumAmount(account))
				throw new InvalidException("Amount is not enough");
			
			AccountTransactionRef ref = new AccountTransactionRef(account, -1 * amount);
			accountTransactionRefService.save(ref);
			
			accountOtp.setExpire(true);
			accountOtpService.save(accountOtp);
			
			return ref;
		}
	}

	private Integer randomOtp(){
		int value = Integer.valueOf(RandomStringUtils.randomNumeric(5));
		return value;
	}
	
	private AccountOtp isOtpExists(Account account, Integer otp){
		AccountOtp res = null;
		if(account != null){
			AccountOtp accountOtp = accountOtpService.getByAccountAndOtp(account, otp);
			if(accountOtp != null){	
				if(new DateTime(accountOtp.getCreatedAt()).plusSeconds(validTime).isAfter(new DateTime()) && !accountOtp.getExpire())
					res = accountOtp;
			}			
		}		
		return res;
	}
}
