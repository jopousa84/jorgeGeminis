package com.jorge.geminis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddAccountDTO {	
	
	Long customerId;
	BigDecimal initialCredit;
}
