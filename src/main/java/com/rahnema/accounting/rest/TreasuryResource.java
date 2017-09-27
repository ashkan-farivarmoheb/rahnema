package com.rahnema.accounting.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahnema.accounting.domain.Account;
import com.rahnema.accounting.domain.AccountOtp;
import com.rahnema.accounting.domain.AccountTransactionRef;
import com.rahnema.accounting.service.TreasuryService;
import  com.rahnema.accounting.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class TreasuryResource {

	private final Logger log = LoggerFactory.getLogger(TreasuryResource.class);

	private final TreasuryService treasuryService;

	public TreasuryResource(TreasuryService treasuryService) {
		this.treasuryService = treasuryService;
	}
	
	@PutMapping(consumes = "application/json", value = "/increaseAmount/{tel}")
	public ResponseEntity<AccountTransactionRef> increaseAmount(@PathVariable String tel, @RequestBody AccountTransactionRef at) throws URISyntaxException {
		log.debug("REST request for increaseAmount : {}", tel, at.getAmount());
		AccountTransactionRef atr = treasuryService.inc(tel, at.getAmount());
		
		return ResponseEntity.created(new URI("/api/increaseAmount/" + atr.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("AccountTransactionRef", atr.getId().toString())).body(atr);
	}
	
	@PutMapping("/decreseSubAmount/{tel}/{opt}")
	public ResponseEntity<AccountTransactionRef> decreseSubAmount(@PathVariable String tel, @PathVariable Integer opt, @RequestBody AccountTransactionRef at) throws URISyntaxException {
		log.debug("REST request for decreseSubAmount : {}", tel, opt, at.getAmount());
		AccountTransactionRef atr = treasuryService.decSub(tel, opt, at.getAmount());
		
		return ResponseEntity.created(new URI("/api/decreseSubAmount/" + atr.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("AccountTransactionRef", atr.getId().toString())).body(atr);
	}
	
	@PostMapping("/decreseReqAmount/{tel}")
	public ResponseEntity<AccountOtp> decreseReqAmount(@PathVariable String tel) throws URISyntaxException {
		log.debug("REST request for decreseReqAmount : {}", tel);
		AccountOtp opt = treasuryService.decReq(tel);
		
		return ResponseEntity.created(new URI("/api/decreseReqAmount/" + opt.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("AccountOtp", opt.getId().toString())).body(opt);
	}
}
