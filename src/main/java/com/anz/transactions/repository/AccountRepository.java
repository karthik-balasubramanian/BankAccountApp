package com.anz.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anz.transactions.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
