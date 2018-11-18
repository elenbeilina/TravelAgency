package com.belch.TravelAgency.service;

import com.belch.TravelAgency.entities.Order;
import com.belch.TravelAgency.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }
    @Override
    public void confirmOrder(Long id) {
        Order order = orderRepository.getOne(id);
        order.setConfirmed(true);
        orderRepository.save(order);
    }
}
