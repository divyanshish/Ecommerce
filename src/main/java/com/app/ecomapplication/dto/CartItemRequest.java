package com.app.ecomapplication.dto;


import lombok.Data;

@Data
public class CartItemRequest {
    private long productID;
    private Integer quantity;
}
