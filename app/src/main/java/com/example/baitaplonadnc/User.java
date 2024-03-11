package com.example.baitaplonadnc;

import android.provider.ContactsContract;

public class User {
    private String email;
    private String password;
    private Integer calo;

    public Integer getCalo() {
        return calo;
    }

    public void setCalo(Integer calo) {
        this.calo = calo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
