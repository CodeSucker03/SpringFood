package com.example.accessing_data_mysql.Service;

import com.example.accessing_data_mysql.Entity.Order;
import com.example.accessing_data_mysql.Response.PaymentResponse;
import com.stripe.exception.StripeException;


public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
