package com.pizzashop.models;

import com.pizzashop.annotations.IsNewLogin;
import com.pizzashop.annotations.SamePasswords;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by bartek on 2/25/17.
 */
@Entity
@SamePasswords(groups = {Manager.RegistrationValidation.class})
public class Manager implements Serializable{
    private Integer managerId;

    @NotNull(groups = {LoginValidation.class,RegistrationValidation.class})
    @Email(groups = {LoginValidation.class,RegistrationValidation.class})
    @IsNewLogin(groups = {RegistrationValidation.class})
    @Length(max=255,groups = {LoginValidation.class,RegistrationValidation.class})
    private String eMail;

    @NotNull(groups = {LoginValidation.class,RegistrationValidation.class})
    @Length(max=30,groups = {LoginValidation.class,RegistrationValidation.class})
    private String password;

    @Transient
    @NotNull(groups = {RegistrationValidation.class})
    @Length(max=30,groups = {RegistrationValidation.class})
    private String passwordConfirmation;

    private boolean activated=false;

    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    @NotNull
    private Date registrationDate=new Date();

    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    @NotNull(groups = {ActivationValidation.class})
    private Date activationDate;

    private List<String> roles= Arrays.asList("ROLE_ADMIN","ADMIN","USER");

    public Manager(Integer managerId, String eMail, String password, boolean activated, Date registrationDate, Date activationDate, List<String> roles) {
        this.managerId = managerId;
        this.eMail = eMail;
        this.password = password;
        this.activated = activated;
        this.registrationDate = registrationDate;
        this.activationDate = activationDate;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Basic
    @Column(name = "activated")
    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registrationDate")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activationDate")
    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
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

    @Override
    public String toString() {
        return "Manager{" +
                "eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public interface LoginValidation{}
    public interface RegistrationValidation{}
    public interface ActivationValidation{}
}
