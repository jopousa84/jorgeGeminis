package com.jorge.geminis.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.geminis.dto.AddAccountDTO;
import com.jorge.geminis.dto.AddAccountDTOResponse;
import com.jorge.geminis.dto.ClientInfoDTO;
import com.jorge.geminis.service.AccountService;
import com.jorge.geminis.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class AccountController {
	
	@Autowired  
	AccountService accountService;
	
	@Autowired
	ClientService clientService;

	
	// Client CRUD operations was not part of this API
	@PostMapping("addCustomer")
	@Operation (summary = "Creates 2 clients, Jorge and mario")
	public ResponseEntity<String> initCustomers() {
	
		String resp = clientService.customerAdds();
		return ResponseEntity.ok(resp);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@Operation (summary = "Creates an Account", 
	description = "If the initial credit is > 0, adds a transaction. Returns an error if the customerId does not exists")
	@PostMapping("/addAccount")
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


	@GetMapping("clientInfo/{customerId}")
	@Operation (summary = "Returns the accounts of the client and related transactions")
	public ResponseEntity<ClientInfoDTO> clientInfo(@PathVariable(value = "customerId") Long customerId) {
	
		ClientInfoDTO clientInfo = clientService.getCustomerAccountInformation(customerId);
		return ResponseEntity.ok(clientInfo);
	}
}
