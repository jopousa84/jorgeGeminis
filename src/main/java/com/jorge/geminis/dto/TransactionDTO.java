package com.jorge.geminis.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDTO {

	Date transactionDate;
	BigDecimal amount;
	String description;
}
