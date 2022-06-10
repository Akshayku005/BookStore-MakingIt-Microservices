package com.bridgelabz.userservice.controller;


import com.bridgelabz.userservice.dto.ResponseDTO;
import com.bridgelabz.userservice.dto.UserDTO;
import com.bridgelabz.userservice.dto.UserLoginDTO;
import com.bridgelabz.userservice.model.UserRegistration;
import com.bridgelabz.userservice.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
    @Autowired
    IUserService userRegistrationService;

    //  Ability to Create account
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        String newUser = userRegistrationService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //    Ability to login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<ResponseDTO>(userRegistrationService.loginUser(userLoginDTO), HttpStatus.OK);
    }

    //    Ability to getAll user
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllUser() {
        List<UserRegistration> listOfUsers = userRegistrationService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:", listOfUsers);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //    Ability to getAll users by token
    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token) {
        List<UserRegistration> listOfUser = userRegistrationService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfUser);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //    Ability to Update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        UserRegistration entity = userRegistrationService.updateRecordById(id, userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully", entity);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    //Ability to verify user by token
    @GetMapping("/verify/{token}")
    ResponseEntity<ResponseDTO> verifyUser(@Valid @PathVariable String token) {
        String userVerification = userRegistrationService.verifyUser(token);
        if (userVerification != null) {
            ResponseDTO responseDTO = new ResponseDTO("User verified :", userVerification);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("User Not verified data:", userVerification);
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }
    }
    //--------------------API Calls for RestTemplate----------------------------//

    @GetMapping("/findById/{userId}")
    public UserRegistration getByIdAPI(@PathVariable Integer userId){
        UserRegistration user =  userRegistrationService.getByIdAPI(userId);
        return user;
    }
}

