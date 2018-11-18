package com.belch.TravelAgency.service;

import com.belch.TravelAgency.entities.Order;

public interface OrderService {
    Order getOrderById(Long id);
    void confirmOrder(Long id);
}
