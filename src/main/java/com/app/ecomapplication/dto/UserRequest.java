package com.app.ecomapplication.dto;

import com.app.ecomapplication.model.UserRole;
import lombok.Data;


@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String Phone;
    private AddressDTO address;
}
