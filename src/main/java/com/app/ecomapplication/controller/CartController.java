package com.app.ecomapplication.controller;


import com.app.ecomapplication.dto.CartItemRequest;
import com.app.ecomapplication.model.CartItem;
import com.app.ecomapplication.service.CartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String>addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request) {
       if(!cartService.addToCart(userId,request)){
           return ResponseEntity.badRequest().body("Product Out of Stock or user not found or product not found");
       }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/items/{productID}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable Long productID) {
        boolean deleted = cartService.deleteItemFromCart(userId, productID);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.getCart(userId));


    }
}
