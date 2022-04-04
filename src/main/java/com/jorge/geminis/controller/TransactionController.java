package com.jorge.geminis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.geminis.dto.ClientInfoDTO;
import com.jorge.geminis.service.ClientService;

@RestController
public class TransactionController {

	
	@Autowired
	ClientService clientService;
	
	@GetMapping("clientInfo/{customerId}")
	public ResponseEntity<ClientInfoDTO> clientInfo(@PathVariable(value = "customerId") Long customerId) {
	
		ClientInfoDTO clientInfo = clientService.getCustomerAccountInformation(customerId);
		return ResponseEntity.ok(clientInfo);
	}
}
