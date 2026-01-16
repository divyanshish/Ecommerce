package com.app.ecomapplication.dto;

import com.app.ecomapplication.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String Phone;
    private UserRole role;
    private AddressDTO address;
}
