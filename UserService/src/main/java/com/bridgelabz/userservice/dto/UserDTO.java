package com.bridgelabz.userservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


public @Data class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "User firstName is Not valid. given first letter in upper case")
    public String firstName;

    @Pattern(regexp = "[A-Za-z\\s]+", message = " User lastname is invalid!. Given first letter in upper case")
    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "address can not be empty")
    private String address;

    @NotEmpty(message = "Password cant be empty")
    private String password;
}

