package com.jorge.geminis;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jorge.geminis.DAO.AccountDAO;
import com.jorge.geminis.DAO.ClientDAO;
import com.jorge.geminis.controller.AccountController;
import com.jorge.geminis.dto.AddAccountDTO;
import com.jorge.geminis.dto.AddAccountDTOResponse;
import com.jorge.geminis.model.Client;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	AccountController acountController;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	
	
	Long addClient() {
		
		Client cliente = new Client();		
		cliente.setAddress("13 St 1954");
		cliente.setName("Jorge");
		cliente.setSurname("Perez");
		cliente.setPhone("5491134567890");
		cliente.setEmail("jorge@hotmail.com");
		clientDAO.save(cliente);
		
		return cliente.getCustomerId();
	}


	@Test
	@Transactional
	void addAccountTest() {
		
		Long clientId = addClient();
		BigDecimal initCredit = new BigDecimal(20.70);		
		
		AddAccountDTO account = new AddAccountDTO(clientId, initCredit);
		
		AddAccountDTOResponse response = acountController.addAccount(account);		

		// compare the balance
		assertEquals(initCredit, accountDAO.getById(response.getAccountID()).getBalance());
	}


	@Test
	void notFoundClientTest() {
		
		BigDecimal initCredit = new BigDecimal(20.70);
		
		// in memory database just created, client won't exist 
		AddAccountDTO account = new AddAccountDTO(1895L, initCredit);
		
		account.setInitialCredit(initCredit);
		
		assertThrows(EntityNotFoundException.class,
			()->{
				acountController.addAccount(account);
			});
	}
	
	
	@Test
	void integrationAddSearch() throws JsonProcessingException, Exception {
		
		Long clientId = addClient();
		
		// will use this value later to compare the result
		BigDecimal initCredit = new BigDecimal(20.70);
		
		AddAccountDTO addAccountDTO = new AddAccountDTO(clientId, initCredit);

		MvcResult response = mockMvc.perform(post("/addAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addAccountDTO)))
				.andDo(print()).andExpect(status().isCreated())   //verificate creation
				.andReturn();
        
        Integer accountId = JsonPath.read(response.getResponse().getContentAsString(), "$.accountID");
        
        String getClientInfo = "/clientInfo/"+ clientId;
        ResultActions resp = mockMvc.perform(get(getClientInfo));
        
        resp.andDo(print())
		        .andExpect(jsonPath("$.accounts[0].accountName", is(clientId + "-" + accountId)))
		        .andExpect(jsonPath("$.accounts[0].balance", is(initCredit.doubleValue())));
	}
}
