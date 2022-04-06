package com.example.autenticacao.model;

import java.util.Objects;

public class User {
    Integer id;
    String username;
    String nome;
    String senha;

    public User(){
    }
    public User(String username, String nome, String senha){
        this.username = username;
        this.nome = nome;
        this.senha = senha;
    }

    public User(Integer id, String username, String nome, String senha) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
