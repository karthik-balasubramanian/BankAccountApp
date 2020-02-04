package com.anz.transactions.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.anz.transactions.model.Account;
import com.anz.transactions.model.Transaction;
import com.anz.transactions.service.BankAccountService;

@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

	@MockBean
	private BankAccountService accountService;

	@Autowired
	private MockMvc mvc;
	
	private Account account;
	private List<Account> accounts;
	
	@BeforeEach
	public void setup() {
		account = new Account();
		account.setAccountName("dummy");
		Transaction tx = new Transaction();
		tx.setAccount(account);
		account.setTransactions(Arrays.asList(tx));
		accounts = new ArrayList<Account>();
		accounts.add(account);
	}

	@Test
	public void testGetAllAccounts() throws Exception {
		
		given(accountService.getAllAccounts()).willReturn(accounts);

		mvc.perform(get("/accounts")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));

	}
	
	@Test
	public void testGetAllTransactionsThrowsAccountNotFoundException() throws Exception {
		Long accountNumber = new Long(1);
		given(accountService.findAccount(accountNumber)).willReturn(Optional.empty());

		mvc.perform(get("/accounts/1/transactions") 
		          .accept(MediaType.APPLICATION_JSON))
		          .andExpect(status().isNotFound());
	}
	
	@Test
	public void testGetAllTransactionsReturnsTransactions() throws Exception {
		Long accountNumber = new Long(1);
		Optional<Account> optional = Optional.of(account);
		given(accountService.findAccount(accountNumber)).willReturn(optional);
	
		mvc.perform(get("/accounts/1/transactions") 
		          .accept(MediaType.APPLICATION_JSON))
		          .andExpect(status().isOk())
		          .andExpect(jsonPath("$", hasSize(1)));
	}
	
}
