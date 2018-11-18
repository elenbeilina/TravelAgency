package com.belch.TravelAgency.repositories;

import com.belch.TravelAgency.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    List<Order> findByUserId(Long userId);
    List<Order> findByTourId(Long tourId);
    Order findOrderById(Long id);

}