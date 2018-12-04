package com.accenture.dto;

import lombok.Data;

@Data
public class RegisterForm {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public RegisterForm() {
    }

    public RegisterForm(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
