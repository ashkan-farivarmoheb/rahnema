package com.rahnema.accounting.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name = AccountTransactionRef.SUM_AMOUNT, query = "select sum(amount) from AccountTransactionRef atr where atr.account= :account")
})

@Entity
@Table(name="acc_transaction_ref")
public class AccountTransactionRef implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5107634870980859440L;

	public static final String SUM_AMOUNT = "atr.SUM_AMOUNT";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Account account;
	
	private String refCode;
	
	private Date refTime;
	
	private Integer amount;
	
	public AccountTransactionRef(){}
	
	public AccountTransactionRef(Account account, Integer amount) {
		this.account = account;
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Date getRefTime() {
		return refTime;
	}

	public void setRefTime(Date refTime) {
		this.refTime = refTime;
	}
	
	@PrePersist
	void preInsert() {
		this.refTime = new Date();		
		this.refCode = generateRefCode();
	}
	
	private String generateRefCode(){
    	String code;
		do {
			code = org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(14);
		} while (!code.matches(".*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*"));
		return code;
    }
}
