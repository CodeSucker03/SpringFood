package com.example.accessing_data_mysql.Service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessing_data_mysql.Entity.Address;
import com.example.accessing_data_mysql.Entity.Cart;
import com.example.accessing_data_mysql.Entity.CartItem;
import com.example.accessing_data_mysql.Entity.Order;
import com.example.accessing_data_mysql.Entity.OrderItem;
import com.example.accessing_data_mysql.Entity.OrderStatus;
import com.example.accessing_data_mysql.Entity.Restaurant;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Repo.AddressRepository;
import com.example.accessing_data_mysql.Repo.OrderItemRepository;
import com.example.accessing_data_mysql.Repo.OrderRepository;
import com.example.accessing_data_mysql.Repo.UserRepository;
import com.example.accessing_data_mysql.Request.OrderRequest;
import com.example.accessing_data_mysql.Service.CartService;
import com.example.accessing_data_mysql.Service.OrderService;
import com.example.accessing_data_mysql.Service.RestaurantService;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shipAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shipAddress);

        // if (!user.getAddress().equals(savedAddress)) {
        // user.setAddress(savedAddress);
        // userRepository.save(user);
        // }
        // Restaurant restaurant =
        // restaurantService.getRestaurantById(order.getRestaurantId());
        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreatedAt(LocalDateTime.now());
        createOrder.setDeliveryAddress(savedAddress);

        // Save it to get a persistent reference with an ID
        Order savedOrder = orderRepository.save(createOrder);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(item.getFood());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());
            orderItem.setOrderStatus(OrderStatus.PENDING);
            orderItem.setIngredients(item.getIngredients());
            orderItem.setRestaurant(item.getRestaurant());
            orderItem.setOrder(savedOrder);
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            // restaurant.getOrderItems().add(savedOrderItem);
            orderItems.add(savedOrderItem);
        }
        BigDecimal totalPrice = cartService.calculateCartTotals(cart);
        savedOrder.setItems(orderItems);
        savedOrder.setTotalPrice(totalPrice);

        return orderRepository.save(savedOrder);
    }

    @Override
    public OrderItem updateOrder(long orderId, OrderStatus orderStatus) throws Exception {
        OrderItem order = orderItemRepository.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found"));
        order.setOrderStatus(orderStatus);
        return orderItemRepository.save(order);

    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<OrderItem> getRestaurantOrderItem(Long restaurantId, OrderStatus orderStatus) throws Exception {
        List<OrderItem> ordersItem = orderItemRepository.findByRestaurantId(restaurantId);
        if (orderStatus != null) {
            ordersItem = ordersItem.stream()
                    .filter(orderItem -> orderItem.getOrderStatus() == orderStatus)
                    .collect(Collectors.toList());
        }
        return ordersItem;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new Exception("order NOT found");
        }
        return optionalOrder.get();
    }

}
