package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.exceptions.UserException;

import java.util.List;

public interface UserService {

    public String createUser(UserDetailsDTO dto, String roleString) throws UserException;

    public UserDetailsDTO userAuthenticationByPhone(String userPhoneNo) throws UserException ;

    public UserDetailsDTO userAuthenticationByEmail(String userEmail) throws UserException ;

    public List<UserDetailsDTO> getAllUsers();

    public UserDetailsDTO getUserDetailsById(String userId);

}
