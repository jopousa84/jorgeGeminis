package com.jorge.geminis.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.geminis.model.Transaction;

public interface TransactionDAO extends JpaRepository<Transaction, Long> {

}
