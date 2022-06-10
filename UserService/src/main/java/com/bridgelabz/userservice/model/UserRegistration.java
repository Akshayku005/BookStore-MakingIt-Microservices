package com.bridgelabz.userservice.model;

import com.bridgelabz.userservice.dto.UserDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_registration")
public @Data class UserRegistration {
    @Id
    @GeneratedValue
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;


    public UserRegistration() {

    }
    public UserRegistration(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.password= userDTO.getPassword();
    }
    public UserRegistration(Integer userId, UserDTO userDTO){
        this.userId=userId;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }

    public UserRegistration(String id, UserDTO userDTO) {
        this.email=id;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address=userDTO.getAddress();
        this.password= userDTO.getPassword();
    }
    public void UpdateUser(UserDTO userDTO) {
        this.firstName= userDTO.getFirstName();
        this.lastName=userDTO.getLastName();
        this.email= userDTO.getEmail();
        this.address=userDTO.getAddress();
        this.password=userDTO.getPassword();
    }
}
