package com.rahnema.accounting.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="account_otp")
public class AccountOtp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8275872278538671840L;

	public AccountOtp(){}
	
	public AccountOtp(Account account, Integer otp, Boolean expire) {
		super();
		this.account = account;
		this.otp = otp;
		this.expire = expire;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Account account;
	
	private Date createdAt;
	
	private Integer otp;
	
	public Boolean getExpire() {
		return expire;
	}

	public void setExpire(Boolean expire) {
		this.expire = expire;
	}

	private Boolean expire;
	
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	
	@PrePersist
	void preInsert() {
		this.createdAt = new Date();
	}
}
