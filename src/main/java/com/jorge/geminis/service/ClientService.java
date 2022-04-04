package com.jorge.geminis.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.geminis.DAO.ClientDAO;
import com.jorge.geminis.dto.AccountDTO;
import com.jorge.geminis.dto.ClientInfoDTO;
import com.jorge.geminis.dto.TransactionDTO;
import com.jorge.geminis.model.Account;
import com.jorge.geminis.model.Client;
import com.jorge.geminis.model.Transaction;


@Service  
public class ClientService {
	
	@Autowired
	ClientDAO clientDAO;
	
	public ClientInfoDTO getCustomerAccountInformation(Long customerId) {
		Client client = clientDAO.getById(customerId);
		ClientInfoDTO clientInfo = new ClientInfoDTO();
		clientInfo.setName(client.getName());
		clientInfo.setSurname(client.getSurname());
		clientInfo.setAccounts(client.getAccounts().stream().map(x -> toAccountDto(x)).collect(Collectors.toList()));
		return clientInfo;
	}

	private AccountDTO toAccountDto(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountName(account.getClient().getCustomerId() + "-" + account.getId());
		accountDTO.setBalance(account.getBalance());
		accountDTO.setTransactions(account.getTransactions().stream().map(x -> toTransactionDto(x)).collect(Collectors.toList()));
		return accountDTO;
	}

	private TransactionDTO toTransactionDto(Transaction x) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(x.getAmount());
		transactionDTO.setDescription(x.getDescription());
		transactionDTO.setTransactionDate(x.getTransactionDate());
		return transactionDTO;
	}
}
