package com.anz.transactions.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.transactions.model.Account;
import com.anz.transactions.repository.AccountRepository;
import com.anz.transactions.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	private Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);
	private AccountRepository accountRepository;
	
	@Autowired
	public BankAccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Account> getAllAccounts() {
		logger.info("START: AccountServiceImpl.getAllAccounts");
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findAccount(Long accountNumber) {
		logger.info("START: AccountServiceImpl.findAccount");
		return accountRepository.findById(accountNumber);
	
	}

}
