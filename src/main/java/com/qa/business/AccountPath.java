package com.qa.business;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.Repository.AccountRepository;

public class AccountPath {
	
	@Inject
	private AccountRepository repository;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String findAllAccounts() {
		return repository.findAllAccounts();
	}

	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return repository.createAccount(account);
	}

	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return repository.updateAccount(account, id);
	}

	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return repository.deleteAccount(id);

	}

	public void setRepository(AccountRepository repository) {
		this.repository = repository;
	}

}
