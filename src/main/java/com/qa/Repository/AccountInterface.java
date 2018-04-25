package com.qa.Repository;

import javax.transaction.Transactional;

public interface AccountInterface {

	String findAllAccounts();

	String createAccount(String newAccount);

	String updateAccount(String accountToUpdate, Long id);

	String deleteAccount(Long id);
	
	String getAccount();

}