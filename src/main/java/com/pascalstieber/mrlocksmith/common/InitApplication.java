package com.pascalstieber.mrlocksmith.common;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.pascalstieber.mrlocksmith.user.UserDAO;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Startup
@Singleton
public class InitApplication {

    private InitApplication() {
    }
    
    @Inject
    private UserDAO userDAO;
    
    @PostConstruct
    private void init(){
	UserEntity user = new UserEntity();
	user.setEmail("pascal.stieber@googlemail.com");
	user.setFirstname("Pascal");
	user.setSurname("Stieber");
	user.setPhonenumber("0176/3232149");
	user.setRole("Admin");
	user.setPassword("HalloWelt");
	userDAO.saveNewUser(user);
	
	
    }
}
