package com.jorge.geminis.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	BigDecimal balance;
	List<TransactionDTO> transactions;
	String accountName;
}
