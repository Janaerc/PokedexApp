package com.example.pokedexapp.data.model;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String login;
    private String senha;

    public LoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public LoginDTO() {
        super();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

