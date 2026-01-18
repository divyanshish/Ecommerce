package com.ecommerce.order.repositories;

import com.ecommerce.order.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(Long userId, String productId);

    void deleteByUserIdAndProductId(String userId, String productId);

    List<CartItem> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
