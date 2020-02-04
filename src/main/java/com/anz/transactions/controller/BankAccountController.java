package com.anz.transactions.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anz.transactions.exceptions.AccountNotFoundException;
import com.anz.transactions.model.Account;
import com.anz.transactions.model.Transaction;
import com.anz.transactions.service.BankAccountService;

@RestController
public class BankAccountController {

	private Logger logger = LoggerFactory.getLogger(BankAccountController.class);
	private BankAccountService accountService;

	public BankAccountController(BankAccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * This service fetches all accounts.
	 * 
	 * @return Accounts List as JSON.
	 */
	@GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		logger.info("START: AccountController.getAllAccounts");
		return accountService.getAllAccounts();
	}

	/**
	 * This service fetches all transactions for an account.
	 * 
	 * @return Transactions List as JSON.
	 */
	@GetMapping(path = "/accounts/{accountNumber}/transactions")
	public List<Transaction> getAllTransactions(@PathVariable Long accountNumber) throws AccountNotFoundException {
		logger.info("START: AccountController.getAllTransactions");
		Optional<Account> account = accountService.findAccount(accountNumber);
		if (!account.isPresent()) {
			throw new AccountNotFoundException("accountNumber:" + accountNumber);
		}
		return account.get().getTransactions();
	}

}
