package com.pizzashop.models.builders;

import com.pizzashop.models.Manager;

import java.util.Date;
import java.util.List;

public class ManagerBuilder {
    private Integer managerId;
    private String eMail;
    private String password;
    private boolean activated;
    private Date registrationDate;
    private Date activationDate;
    private List<String> roles;

    public ManagerBuilder setManagerId(Integer managerId) {
        this.managerId = managerId;
        return this;
    }

    public ManagerBuilder seteMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public ManagerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ManagerBuilder setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public ManagerBuilder setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public ManagerBuilder setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
        return this;
    }

    public ManagerBuilder setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public Manager createManager() {
        return new Manager(managerId, eMail, password, activated, registrationDate, activationDate, roles);
    }
}