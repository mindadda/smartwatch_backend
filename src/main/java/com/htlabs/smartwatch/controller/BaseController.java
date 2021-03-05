package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.utils.ErrorMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BaseController {

    protected String getAuthToken() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isEmpty(userId))
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    ErrorMessages.AUTHENTICATION_TOKEN_MISSING);

        return userId;
    }

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}