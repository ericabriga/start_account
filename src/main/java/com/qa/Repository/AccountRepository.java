package com.qa.Repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import antlr.collections.List;


@Transactional(SUPPORTS)
@Default
public class AccountRepository implements AccountInterface {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Inject
	private JSONUtil util;
	
	
    private Account findAccount(Long id) {
        return em.find(Account.class, id);
    }
    
    @Override
	public String findAllAccounts() {
        TypedQuery<Account> query=em.createQuery("SELECT ac FROM Account ac ORDER BY ac.firstName DESC", Account.class);
        List accounts = (List) query.getResultList();
        return util.getJSONForObject(accounts);
    
    }
    
    /*public Long countAll() {
        TypedQuery<Long> query=em.createQuery("SELECT COUNT(ac) FROM Account ac", Long.class);
        return query.getSingleResultList();
    
    }*/
    
    @Override
	@Transactional(REQUIRED)
    public String createAccount (String newAccount) {
    	Account account = util.getObjectForJSON(newAccount, Account.class);
        em.persist(account);
        return "{\"Account added\"}";
    }
    
    @Override
	@Transactional(REQUIRED)
    public String updateAccount (String accountToUpdate, Long id) {
    	Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountListed = findAccount(id);
		if (accountToUpdate != null) {
			accountListed = updatedAccount;
        em.merge(accountListed);
		}
        return "{\"Account updated\"}";
    }
    
    @Override
	@Transactional(REQUIRED)
    public String deleteAccount(Long id) {
        em.remove(em.getReference(Account.class, id));
        return "{\"Account deleted\"}";
       
    }

    public void setManager(EntityManager em) {
		this.em = em;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
}
