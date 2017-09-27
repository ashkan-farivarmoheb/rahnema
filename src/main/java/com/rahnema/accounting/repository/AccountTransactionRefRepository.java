package com.rahnema.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountTransactionRef;

@Repository
public interface AccountTransactionRefRepository extends JpaRepository<AccountTransactionRef, Long>{

	@Query(name=AccountTransactionRef.SUM_AMOUNT)
	Integer sumAmount(@Param("account") Account account);
}
