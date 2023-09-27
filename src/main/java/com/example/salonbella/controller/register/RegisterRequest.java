package com.example.salonbella.controller.register;

public class RegisterRequest {
    private String name;
    private String surname;
    private String number;
    private String email;
    private String username;
    private String password;

    public RegisterRequest(String name, String surname, String number, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
