package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDetailsDTO {

    private String userId;

    private String name;

    private String role;

    private String email;

    private String phoneNo;

    private String password;

}
