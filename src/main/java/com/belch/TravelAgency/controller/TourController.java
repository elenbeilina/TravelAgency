package com.belch.TravelAgency.controller;

import com.belch.TravelAgency.entities.*;
import com.belch.TravelAgency.repositories.TourRepository;
import com.belch.TravelAgency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Date;


@Controller
public class TourController {

    @Autowired
    TourRepository tourRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/tours")
    public String tourList(Model model){
        model.addAttribute("tours", tourRepository.findAll());
        return "tours";
    }

    @PostMapping("/saveTour")
    public String saveTour(Tour tour, @RequestParam String name, @RequestParam String description,
                           @RequestParam String location, @RequestParam Date startDate,
                           @RequestParam Date endDate, @RequestParam int countLimit,
                           @RequestParam Long id) {
        if(id != null)
        {
            Tour editTour = tourRepository.getOne(tour.getId());
            editTour.setCountLimit(countLimit);
            editTour.setDescription(description);
            editTour.setLocation(location);
            editTour.setName(name);
            editTour.setEndDate(endDate);
            editTour.setStartDate(startDate);

            tourRepository.save(editTour);
        }
        else
        {
            tourRepository.save(tour);
        }
        return "redirect:/admin/tourform";
    }

    @GetMapping("tour/new")
    public ModelAndView addNewTour() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tour", new Tour());
        modelAndView.setViewName("admin/addTour");
        return modelAndView;
    }

    @GetMapping("tour/edit/{id}")
    public ModelAndView editTour(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tour", tourRepository.getOne(id));
        modelAndView.setViewName("admin/addTour");
        return modelAndView;
    }

    @GetMapping("/admin/tourform")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tours",tourRepository.findAll());
        modelAndView.setViewName("admin/tourform");
        return modelAndView;
    }

}
