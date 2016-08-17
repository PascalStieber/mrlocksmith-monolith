package com.pascalstieber.mrlocksmith.auftrag;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AuftragBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private boolean KeyNotAvailable;

    @PostConstruct
    public void init(){
	setKeyNotAvailable(false);
    }
    
    
    
    public void changeValueKeyNotAvailable(){
	if (KeyNotAvailable == true){
	    setKeyNotAvailable(false);
	}else{
	    setKeyNotAvailable(true);
	}
	System.out.println("growl!" + isKeyNotAvailable());
    }
    
    
    
    public boolean isKeyNotAvailable() {
	return KeyNotAvailable;
    }

    public void setKeyNotAvailable(boolean keyNotAvailable) {
	KeyNotAvailable = keyNotAvailable;
    }
    
    
}
