package com.example.accessing_data_mysql.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessing_data_mysql.Entity.CartItem;
import com.example.accessing_data_mysql.Entity.Order;
import com.example.accessing_data_mysql.Entity.User;
import com.example.accessing_data_mysql.Request.OrderRequest;
import com.example.accessing_data_mysql.Response.PaymentResponse;
import com.example.accessing_data_mysql.Service.OrderService;
import com.example.accessing_data_mysql.Service.PaymentService;
import com.example.accessing_data_mysql.Service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

     @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;


    // USE PAYMENT METHOD
    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
    @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);
        PaymentResponse response = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // DEFAULT 
    // @PostMapping("/order")
    // public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
    // @RequestHeader("Authorization") String jwt) throws Exception{
    //     User user = userService.findUserByJwtToken(jwt);
    //     Order order = orderService.createOrder(req, user);
    //     return new ResponseEntity<>(order, HttpStatus.CREATED);
    // }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
    @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
