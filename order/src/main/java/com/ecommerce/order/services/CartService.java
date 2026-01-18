package com.ecommerce.order.services;







import com.ecommerce.order.dtos.CartItemRequest;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartItemRepository cartItemRepository;
    public boolean addToCart(Long userId, CartItemRequest request) {
//        //look for product
//        Optional<Product> productOpt = productRepository.findById(request.getProductID());
//        if (productOpt.isEmpty()) {
//            return false;
//        }
//        Product product = productOpt.get();
//        if (product.getQuantity() < request.getQuantity()) {
//            return false;
//        }
//
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if (userOpt.isEmpty()) {
//            return false;
//        }

       // User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductID());

        if (existingCartItem != null) {
            // Update the Quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);

        }

        else {
            // create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(Long.valueOf(request.getProductID()));
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);

        }
        return true;
    }

    public boolean deleteItemFromCart(Long userId, String productId) {
       CartItem cartItem =  cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }

        return false;
    }

    public List<CartItem> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
