package com.jorge.geminis.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInfoDTO {

	String name;
	String surname;
	List <AccountDTO> accounts;
}
