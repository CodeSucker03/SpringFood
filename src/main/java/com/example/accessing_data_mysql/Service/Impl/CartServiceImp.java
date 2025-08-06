package com.example.accessing_data_mysql.Service.Impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.Cart;
import com.example.accessing_data_mysql.Entity.CartItem;
import com.example.accessing_data_mysql.Entity.Food;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Repo.CartItemRepository;
import com.example.accessing_data_mysql.Repo.CartRepository;
import com.example.accessing_data_mysql.Repo.RestaurantRepository;
import com.example.accessing_data_mysql.Request.AddCartItemRequest;
import com.example.accessing_data_mysql.Service.CartService;
import com.example.accessing_data_mysql.Service.FoodService;
import com.example.accessing_data_mysql.Service.RestaurantService;
import com.example.accessing_data_mysql.Service.UserService;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    // @Autowired
    // private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt, Long restaurantId) throws Exception {
        // TODO Auto-generated method stub
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<String> ingredients = req.getIngredients();
        Cart cart = cartRepository.findByUserId(user.getId());
        // if exist the chaange quantity
        for (CartItem cartItem : cart.getItems()) {
            Set<String> itemIngredients = cartItem.getIngredients() == null ? Collections.emptySet()
                    : new HashSet<>(cartItem.getIngredients());
            Set<String> requestIngredients = ingredients == null ? Collections.emptySet() : new HashSet<>(ingredients);

            if (cartItem.getFood().equals(food)
                    && itemIngredients.equals(requestIngredients)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        // else new
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setIngredients(ingredients);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setRestaurant(restaurant);
        newCartItem.setTotalPrice(BigDecimal.valueOf(req.getQuantity()).multiply(food.getPrice()));

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        BigDecimal quantityBD = BigDecimal.valueOf(quantity);
        BigDecimal price = item.getFood().getPrice();
        BigDecimal total = quantityBD.multiply(price);

        item.setTotalPrice(total);

        return cartItemRepository.save(item);
    }

    // @Override
    // public Cart updateCartItemQuantity(Cart cart,Long cartItemId, int quantity)
    // throws Exception {
    // Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
    // if (cartItem.isEmpty()) {
    // throw new Exception("Cart item not found");
    // }
    // CartItem item = cartItem.get();
    // item.setQuantity(quantity);
    // BigDecimal quantityBD = BigDecimal.valueOf(quantity);
    // BigDecimal price = item.getFood().getPrice();
    // BigDecimal total = quantityBD.multiply(price);

    // item.setTotalPrice(total);
    // cartItemRepository.save(item);

    // }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByUserId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItem.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public BigDecimal calculateCartTotals(Cart cart) throws Exception {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            BigDecimal itemTotal = BigDecimal
                    .valueOf(cartItem.getQuantity())
                    .multiply(cartItem.getFood().getPrice());

            total = total.add(itemTotal);
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new Exception("Cart item not found with id: " + id);
        }
        return optionalCart.get();

    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

}
