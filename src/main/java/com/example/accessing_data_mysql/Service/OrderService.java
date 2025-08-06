package com.example.accessing_data_mysql.Service;

import java.util.List;

import com.example.accessing_data_mysql.Entity.Order;
import com.example.accessing_data_mysql.Entity.OrderItem;
import com.example.accessing_data_mysql.Entity.OrderStatus;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.OrderRequest;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public OrderItem updateOrder(long orderId, OrderStatus orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
    
    public List<OrderItem> getRestaurantOrderItem(Long restaurantId, OrderStatus orderStatus) throws Exception;
}
    
