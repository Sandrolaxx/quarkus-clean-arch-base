package com.aktie.domain.entities;

import java.util.List;

public class UserBO {
    
    private String name;

    private String document;

    private List<AccountBO> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<AccountBO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountBO> accounts) {
        this.accounts = accounts;
    }

}
