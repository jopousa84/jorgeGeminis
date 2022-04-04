package com.jorge.geminis.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.geminis.dto.AddAccountDTO;
import com.jorge.geminis.dto.AddAccountDTOResponse;
import com.jorge.geminis.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired  
	AccountService accountService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value= "/addAccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public AddAccountDTOResponse addAccount(@RequestBody AddAccountDTO account) {

		Long accountId = accountService.createAccount(account.getCustomerId());

		if (!account.getInitialCredit().equals(BigDecimal.ZERO)) {
			accountService.createTransaction(accountId, account.getInitialCredit());
		};
		
		AddAccountDTOResponse response = new AddAccountDTOResponse();
		response.setAccountID(accountId);
		response.setDetails("Account created successfully");
		
		return response;
	}
	
}
