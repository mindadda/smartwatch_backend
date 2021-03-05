package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.entity.UserDetails;
import com.htlabs.smartwatch.entity.converter.UserConverter;
import com.htlabs.smartwatch.exceptions.UserException;
import com.htlabs.smartwatch.repository.UserDetailRepository;
import com.htlabs.smartwatch.service.UserService;
import com.htlabs.smartwatch.utils.EncryptionUtils;
import com.htlabs.smartwatch.utils.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public String createUser(UserDetailsDTO dto, String roleString) throws UserException {
        UserDetails userDetail = new UserDetails();
        if (userDetailRepository.findByEmail(dto.getEmail()) != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.MAIL_ALREADY_EXISTS);

        else if (userDetailRepository.findByPhoneNo(dto.getPhoneNo()) != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.PHONE_ALREADY_EXISTS);

        log.info("Creating User");
        UserConverter.getUserDetailEntityFromDto(dto, userDetail);
        userDetail.setUserId(UUID.randomUUID().toString());
        userDetail.setRole(roleString);
        userDetail.setPassword(EncryptionUtils.encrypt(dto.getPassword()));
//        if (!roleString.equals(Roles.USER.name()))
        userDetail.setCreatedAt(new Date());
        userDetail.setUpdatedAt(new Date());
        userDetailRepository.save(userDetail);
        return userDetail.getUserId();
    }

    @Override
    public UserDetailsDTO userAuthenticationByPhone(String userPhoneNo) throws UserException {
        UserDetails userDetail = userDetailRepository.findByPhoneNo(userPhoneNo);
        if (userDetail == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_CREDENTIALS);
        UserDetailsDTO userDetailDto = UserConverter.getUserDetailDtoFromEntity(userDetail);
        //updateLastLoginTime(userDetail);
        return userDetailDto;
    }

    @Override
    public UserDetailsDTO userAuthenticationByEmail(String userEmail) throws UserException {
        UserDetails userDetail = userDetailRepository.findByEmail(userEmail);
        if (userDetail == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_CREDENTIALS);

        UserDetailsDTO userDetailDto = UserConverter.getUserDetailDtoFromEntity(userDetail);
        //updateLastLoginTime(userDetail);
        return userDetailDto;
    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {
        log.info("Retrieving all the Users.");
        List<UserDetails> userDetail = userDetailRepository.findAll();
        return UserConverter.getListUserDetailsDtoFromEntityList(userDetail);
    }

    @Override
    public UserDetailsDTO getUserDetailsById(String userId) {
        log.info("Retrieving User Details.");
        UserDetails userDetails = userDetailRepository.findById(userId).orElse(null);
        return UserConverter.getUserDetailDtoFromEntity(userDetails);
    }
}
