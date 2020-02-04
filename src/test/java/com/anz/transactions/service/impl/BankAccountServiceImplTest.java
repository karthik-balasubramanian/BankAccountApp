package com.anz.transactions.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anz.transactions.model.Account;
import com.anz.transactions.repository.AccountRepository;
import com.anz.transactions.service.BankAccountService;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

	private BankAccountService bankAccountService;
	
	@Mock
	private AccountRepository accountRepository;
	
	@BeforeEach
	public void setup() {
		bankAccountService = new BankAccountServiceImpl(accountRepository);
	}
	
	@Test
	public void testGetAllAccounts() {

		willReturn(new ArrayList<Account>()).given(accountRepository).findAll();
		
		List<Account> accounts = bankAccountService.getAllAccounts();
		
		verify(accountRepository, times(1)).findAll();
		assertThat(accounts).hasSize(0);
	}
	
	@Test
	public void testFindAccount() {
		Long accountNumber = new Long(1);
		willReturn(Optional.empty()).given(accountRepository).findById(accountNumber);
		
		Optional<Account> optionalAccount = bankAccountService.findAccount(accountNumber);
		
		verify(accountRepository, times(1)).findById(accountNumber);
		assertThat(optionalAccount).isEmpty();
	}
}
