package com.pizzashop.models;

import com.pizzashop.models.enums.ClientType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Entity
public class Client implements Serializable {
    private Integer clientId;
    @NotNull
    @Length(max=30)
    private String firstName, surname;
    @NotNull
    @Email
    @Length(max=255)
    private String eMail;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    public Client(String firstName, String surname, String eMail, ClientType clientType) {
        this.firstName = firstName;
        this.surname = surname;
        this.eMail = eMail;
        this.clientType = clientType;
    }

    public Client() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clientId")
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "clientType")
    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(eMail, client.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eMail);
    }
}
