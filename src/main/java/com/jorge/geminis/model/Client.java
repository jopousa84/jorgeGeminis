package com.jorge.geminis.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long customerId;
	@Column
	String name;
	@Column
	String surname;
	@Column
	String address;
	@Column
	String phone;
	@Column
	String email;
	@OneToMany (mappedBy = "client")
	List<Account> accounts;
}
