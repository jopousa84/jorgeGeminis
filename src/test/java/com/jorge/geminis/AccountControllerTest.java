package com.jorge.geminis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jorge.geminis.DAO.AccountDAO;
import com.jorge.geminis.DAO.ClientDAO;
import com.jorge.geminis.controller.AccountController;
import com.jorge.geminis.dto.AddAccountDTO;
import com.jorge.geminis.dto.AddAccountDTOResponse;
import com.jorge.geminis.model.Client;

@SpringBootTest
public class AccountControllerTest {

	@Autowired
	AccountController acountController;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	ClientDAO clientDAO;
	

	@Test
	@Transactional
	void addAccountTest() {
		
		// add client 1
		Client cliente = new Client();		
		cliente.setAddress("13 St 1954");
		cliente.setName("Jorge");
		cliente.setSurname("Perez");
		cliente.setPhone("5491134567890");
		cliente.setEmail("jorge@hotmail.com");
		clientDAO.save(cliente);
		
		AddAccountDTO account = new AddAccountDTO();
		account.setCustomerId(1L);
		
		BigDecimal initCredit = new BigDecimal(20.70);
		
		account.setInitialCredit(initCredit);
		
		AddAccountDTOResponse response = acountController.addAccount(account);		

		assertEquals(initCredit, accountDAO.getById(response.getAccountID()).getBalance());
	}


	@Test
	void notFoundClientTest() {
		
		AddAccountDTO account = new AddAccountDTO();
		account.setCustomerId(9L);
		
		BigDecimal initCredit = new BigDecimal(20.70);
		
		account.setInitialCredit(initCredit);
		
		assertThrows(EntityNotFoundException.class,
			()->{
				acountController.addAccount(account);
			});
	}
}
