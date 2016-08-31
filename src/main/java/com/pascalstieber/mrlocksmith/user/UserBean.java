package com.pascalstieber.mrlocksmith.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@ManagedBean
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<UserEntity> allUsers;
    
    @Inject
    private UserDAO userDAO;
    

    public List<UserEntity> getAllUsers(){
	return userDAO.fetchAllUsers();
    }
    
    public void setAllUsers(List<UserEntity> allUsers) {
	this.allUsers = allUsers;
    }
    
    
}
