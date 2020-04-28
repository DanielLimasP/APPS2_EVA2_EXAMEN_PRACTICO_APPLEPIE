package com.example.apps2_eva2_examen_practico_applepie;

public class User {

    private String lastname;
    private String name;
    private String username;
    private String password;

    public User(String lastname, String name, String username, String password){
        this.lastname = lastname;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
