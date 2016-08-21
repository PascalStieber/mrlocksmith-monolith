package com.pascalstieber.mrlocksmith.order;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.user.UserBean;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Named
@SessionScoped
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean KeyNotAvailable;
    private String HomeOrCar = "";
    private String Door = "";
    private boolean Express;
    private UserEntity User;
    private AdressEntity Adress;

    @PostConstruct
    public void init() {
	setKeyNotAvailable(false);
	setExpress(false);
	setHomeOrCar("home");
	setDoor("normal");

	setUser(new UserEntity());
	setAdress(new AdressEntity());

    }

    public String getHomeOrCarLink() {
	if ("home".equals(HomeOrCar)) {
	    return "./questionnaireHome2.xhtml?faces-redirect=true";
	} else {
	    return "./questionnaireCar2.xhtml?faces-redirect=true";
	}
    }

    public void changeExpress() {
	if (Express) {
	    Express = false;
	} else {
	    Express = true;
	}
    }

    public boolean isKeyNotAvailable() {
	return KeyNotAvailable;
    }

    public void setKeyNotAvailable(boolean keyNotAvailable) {
	KeyNotAvailable = keyNotAvailable;
    }

    public String getHomeOrCar() {
	return HomeOrCar;
    }

    public void setHomeOrCar(String homeOrCar) {
	HomeOrCar = homeOrCar;
    }

    public String getDoor() {
	return Door;
    }

    public void setDoor(String door) {
	this.Door = door;
    }

    public boolean isExpress() {
	return Express;
    }

    public void setExpress(boolean express) {
	this.Express = express;
    }

    public UserEntity getUser() {
	return User;
    }

    public void setUser(UserEntity user) {
	User = user;
    }

    public AdressEntity getAdress() {
	return Adress;
    }

    public void setAdress(AdressEntity adress) {
	Adress = adress;
    }

}
