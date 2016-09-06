package com.pascalstieber.mrlocksmith.common;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.pascalstieber.mrlocksmith.contractor.ContractorDAO;
import com.pascalstieber.mrlocksmith.contractor.ContractorEntity;
import com.pascalstieber.mrlocksmith.user.UserDAO;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Startup
@Singleton
public class InitApplication {

    private InitApplication() {
    }
    
    @Inject
    private UserDAO userDAO;
    @Inject
    private ContractorDAO contractorDAO;
    
    
    @PostConstruct
    private void init(){
	UserEntity user = new UserEntity();
	user.setEmail("hallo@googlemail.com");
	user.setFirstname("Pascal");
	user.setSurname("Stieber");
	user.setPhonenumber("0176/3232149");
	user.setRole("Admin");
	user.setPassword("a");
	userDAO.saveNewUser(user);
	
	ContractorEntity contractor = new ContractorEntity();
	contractor.setEmail("unternehmen@gmail.com");
	contractor.setCompanyname("DiConDev");
	contractor.setHolderfirstname("Pascal");
	contractor.setHoldersurname("Stieber");
	contractor.setPhonenumber("1464665");
	contractor.setRole("Admin");
	contractor.setFirstname("Pascal");
	contractor.setSurname("Stieber");
	contractor.setPassword("a");
	contractorDAO.saveNewContractor(contractor);
    }
}
