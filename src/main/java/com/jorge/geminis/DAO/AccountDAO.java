package com.jorge.geminis.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.geminis.model.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {

}
