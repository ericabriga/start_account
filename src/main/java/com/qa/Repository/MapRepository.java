package com.qa.Repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class MapRepository implements AccountInterface {

	private Long COUNT = 1L;
	private Map<Long, Account> MapAccount;
	private Long id;

	@Inject
	private JSONUtil util;

	public MapRepository() {
		this.MapAccount = new HashMap<Long, Account>();
		id = COUNT;
		
	}
	@Override
	public String findAllAccounts() {
		return util.getJSONForObject(MapAccount.values());
	}

	@Override
	public String createAccount(String newAccount) {
		id++;
		Account account = util.getObjectForJSON(newAccount, Account.class);
		MapAccount.put(id, account);
		return "{\"Account added\"}";	
		
	}

	@Override
	public String updateAccount(String accountToUpdate, Long id) {
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		MapAccount.put(id, updatedAccount);
		return "{\"Account updated\"}";
	}

	@Override
	public String deleteAccount(Long id) {
		MapAccount.remove(id);
		return "{\"accout removed\"}";
	}
	
	@Override
    public String getAccount() {
        return "To switch back to the default, comment out the 'alternatives' tag in the WEB-INF/beans.xml file";
    }

}
