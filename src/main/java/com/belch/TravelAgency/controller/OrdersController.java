package com.belch.TravelAgency.controller;

import com.belch.TravelAgency.entities.*;
import com.belch.TravelAgency.entities.Tour;
import com.belch.TravelAgency.repositories.OrderRepository;
import com.belch.TravelAgency.repositories.TourRepository;
import com.belch.TravelAgency.repositories.UserRepository;
import com.belch.TravelAgency.service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import  org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private OrderServiceImp orderService;


    @GetMapping("/orders")
    public String tourList(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @GetMapping("/admin/ordersform")
    public ModelAndView ordersList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",orderRepository.findAll());
        modelAndView.setViewName("admin/ordersform");
        return modelAndView;
    }

    @PostMapping("createorder")
    public String saveOrder(Model model, @RequestParam Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Date carrentDate = new Date();

        User user = userRepository.findByUsername(auth.getName());
        Order order = new Order();
        order.setWho_take(user);
        Tour tour = tourRepository.getOne(id);
        order.setTours(tour);

        int amountOfvauchers = tour.getCountLimit();
        amountOfvauchers--;

        if(amountOfvauchers > 0)
        {
            order.setTimeKey(carrentDate);
            order.setConfirmed(false);
            orderRepository.save(order);
            tour.setCountLimit(amountOfvauchers);
            tourRepository.save(tour);
        }
        model.addAttribute("tours", tourRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());

        return "orders";
    }


    @RequestMapping("/removeorder/{id}")
    public String deleteOrder(Model model,  @PathVariable Long id) throws ParseException {

        Date carrentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Long tour_id = orderRepository.findOrderById(id).getTours().getId();

        Tour tour = tourRepository.getOne(tour_id);

        carrentDate = sdf.parse(sdf.format(carrentDate));
        Date tourDate = sdf.parse(tour.getEndDate().toString());

        if(carrentDate.before(tourDate))
        {
            orderRepository.deleteById(id);
            tour.setCountLimit(tour.getCountLimit()+1);
            tourRepository.save(tour);
        }
        else
        {
            model.addAttribute("notSuccessMessage", "You can't delete your order, because tour ended");
        }

        model.addAttribute("tours", tourRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());

        return "orders";
    }

    @RequestMapping("order/confirm/{id}")
    public String confirm(@PathVariable Long id) {
        orderService.confirmOrder(id);
        return "redirect:/admin/ordersform";
    }


}
