package com.example.accessing_data_mysql.Service;

import java.math.BigDecimal;

import com.example.accessing_data_mysql.Entity.Cart;
import com.example.accessing_data_mysql.Entity.CartItem;
import com.example.accessing_data_mysql.Request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt, Long restaurantId) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public BigDecimal calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
