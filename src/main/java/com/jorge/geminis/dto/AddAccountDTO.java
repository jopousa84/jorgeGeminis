package com.jorge.geminis.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountDTO {	
	
	Long customerId;
	BigDecimal initialCredit;
}
