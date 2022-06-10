package com.bridgelabz.userservice.service;



import com.bridgelabz.userservice.dto.ResponseDTO;
import com.bridgelabz.userservice.dto.UserDTO;
import com.bridgelabz.userservice.dto.UserLoginDTO;
import com.bridgelabz.userservice.model.UserRegistration;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);
    String verifyUser(String token);
    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);

    UserRegistration updateUser(String email, UserDTO userDTO);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);

    UserRegistration getByIdAPI(Integer userId);
}
