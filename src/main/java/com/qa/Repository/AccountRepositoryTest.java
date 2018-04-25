package com.qa.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import com.qa.util.JSONUtil;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryTest {

	@InjectMocks
	private AccountRepository repo;

	@Mock
	private EntityManager em;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";
	
	@BeforeClass
	public void setUpBeforeClass() {
		repo.setManager(em);
		util = new JSONUtil();
		repo.setUtil(util);
	}


	@Test
	public void testCreateAccount() {
		String addedAccount = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(addedAccount, "{\"Account added\"}");
	}
	
	@Test
	public void testUpdateAccount() {
		String updatedAccount = repo.updateAccount(MOCK_OBJECT,1L);
		Assert.assertEquals(updatedAccount, "{\"Account updated\"}");
	}
	
	@Test
	public void testDeleteAccount() {
		String deletedAccount = repo.deleteAccount(1L);
		Assert.assertEquals(deletedAccount, "{\"Account deleted\"}");

	}
		
}
