package com.bridgelabz.orderservice.model;

import com.bridgelabz.orderservice.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public User(UserDTO userDTO){
        this.userId=getUserId();
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }
    public User(Integer userId, UserDTO userDTO){
        this.userId=userId;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }

    public User(String email_id, UserDTO userDTO) {
        this.email=email_id;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address=userDTO.getAddress();
        this.password= userDTO.getPassword();
    }

    public void updateUser(UserDTO user) {
        this.firstName= user.getFirstName();
        this.lastName=user.getLastName();
        this.email= user.getEmail();
        this.address=user.getAddress();
        this.password=user.getPassword();
    }
}
