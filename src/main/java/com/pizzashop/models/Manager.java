package com.pizzashop.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bartek on 2/25/17.
 */
@Entity
public class Manager implements Serializable{
    private Integer managerId;

    @NotNull
    @Email
    @Length(max=255)
    private String eMail;

    @NotNull
    @Length(max=30)
    private String password;

    private List<String> roles;

    public Manager(Integer managerId, String eMail, String password, List<String> roles) {
        this.managerId = managerId;
        this.eMail = eMail;
        this.password = password;
        this.roles = roles;
    }

    public Manager() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "managerId")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "eMail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ElementCollection(fetch= FetchType.EAGER)
    @Column(name = "roles")
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        return eMail != null ? eMail.equals(manager.eMail) : manager.eMail == null;
    }

    @Override
    public int hashCode() {
        return eMail != null ? eMail.hashCode() : 0;
    }
}
