package com.pascalstieber.mrlocksmith.order;

<<<<<<< Upstream, based on origin/master
public class OrderEntity {

=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
>>>>>>> 235b76e - wildfly maven integration completet - visual changes in gui - added database facets - exported run_configurations - renamed config.cli to jboss_config.cli
}
