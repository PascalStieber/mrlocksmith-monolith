package com.pascalstieber.mrlocksmith.common;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractMRLSEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }
}
