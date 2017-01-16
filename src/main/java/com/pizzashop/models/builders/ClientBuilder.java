package com.pizzashop.models.builders;

import com.pizzashop.models.Client;
import com.pizzashop.models.enums.ClientType;

public class ClientBuilder {
    private String firstName;
    private String surname;
    private String eMail;
    private ClientType clientType;

    public ClientBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder seteMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public ClientBuilder setClientType(ClientType clientType) {
        this.clientType = clientType;
        return this;
    }

    public Client createClient() {
        return new Client(firstName, surname, eMail, clientType);
    }
}