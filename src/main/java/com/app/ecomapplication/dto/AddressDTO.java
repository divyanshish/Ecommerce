package com.app.ecomapplication.dto;

import com.app.ecomapplication.model.UserRole;
import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String country;
    private String Zipcode;
}
