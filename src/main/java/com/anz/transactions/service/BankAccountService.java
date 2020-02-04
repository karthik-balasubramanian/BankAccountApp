package com.anz.transactions.service;

import java.util.List;
import java.util.Optional;

import com.anz.transactions.model.Account;


public interface BankAccountService {
	
	public List<Account> getAllAccounts() ;
	
	public Optional<Account> findAccount(Long accountNumber);
}
