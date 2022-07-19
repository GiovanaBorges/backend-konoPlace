package com.konoPlace.konoplace.models;

public class UserLogin {
    private Long id;
    private String email;
    private String pass;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String senha) {
        this.pass = senha;
    }


}
