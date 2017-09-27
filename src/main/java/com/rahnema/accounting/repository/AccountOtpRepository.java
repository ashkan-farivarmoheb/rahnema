package com.rahnema.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountOtp;

@Repository
public interface AccountOtpRepository extends JpaRepository<AccountOtp, Long>{

	AccountOtp findOneByAccountAndOtpAndExpire(Account account, Integer otp, boolean expire);
}
