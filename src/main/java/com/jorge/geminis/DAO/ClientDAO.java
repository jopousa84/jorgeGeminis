package com.jorge.geminis.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.geminis.model.Client;

public interface ClientDAO extends JpaRepository<Client, Long> {

}
