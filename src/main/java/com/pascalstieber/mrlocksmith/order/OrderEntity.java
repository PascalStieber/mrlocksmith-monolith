package com.pascalstieber.mrlocksmith.order;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;

@Entity
public class OrderEntity extends AbstractMRLSEntity{

    private static final long serialVersionUID = 1L;

    private boolean keyNotAvailable;
    private String homeOrCar = "";
    private String door = "";
    private boolean express;

    public boolean isKeyNotAvailable() {
        return keyNotAvailable;
    }
    public void setKeyNotAvailable(boolean keyNotAvailable) {
        this.keyNotAvailable = keyNotAvailable;
    }
    public String getHomeOrCar() {
        return homeOrCar;
    }
    public void setHomeOrCar(String homeOrCar) {
        this.homeOrCar = homeOrCar;
    }
    public String getDoor() {
        return door;
    }
    public void setDoor(String door) {
        this.door = door;
    }
    public boolean isExpress() {
        return express;
    }
    public void setExpress(boolean express) {
        this.express = express;
    }
    
    
}
