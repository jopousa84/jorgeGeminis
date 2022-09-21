package com.jorge.geminis.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.geminis.DAO.AccountDAO;
import com.jorge.geminis.DAO.ClientDAO;
import com.jorge.geminis.DAO.TransactionDAO;
import com.jorge.geminis.model.Account;
import com.jorge.geminis.model.Client;
import com.jorge.geminis.model.Transaction;


@Service  
public class AccountService {
	
	@Autowired
	ClientDAO clientDAO;
	@Autowired  
	AccountDAO accountDAO;
	@Autowired
	TransactionDAO transactionDAO;


	@Transactional
	public Long createAccount(Long customerId) {
		final Client client = clientDAO.getById(customerId);
		client.getName();// check if client exists, throws exception if not
		Account newAccount = new Account();
		// later the createTransaction will update the balance
		newAccount.setBalance(BigDecimal.ZERO);
		newAccount.setClient(client);
		
		return accountDAO.save(newAccount).getId();
	}


	@Transactional
	public void createTransaction(Long accountId, BigDecimal initialCredit) {

		Account account = accountDAO.getById(accountId);
		account.setBalance(account.getBalance().add(initialCredit));
		accountDAO.save(account);
		
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setDescription("Initial Credit");
		transaction.setAmount(initialCredit);
		transaction.setTransactionDate(new Date());
		transactionDAO.save(transaction);
	}
}
