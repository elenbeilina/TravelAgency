package com.belch.TravelAgency.controller;

import javax.validation.Valid;
import com.belch.TravelAgency.entities.User;
import com.belch.TravelAgency.repositories.UserRepository;
import com.belch.TravelAgency.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/admin/users")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users",userRepository.findAll());
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user) {
        ModelAndView modelAndView = new ModelAndView();
//
        userServiceImp.saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");

        return modelAndView;
    }


    @PostMapping("/user/edit")
    public String saveUser(@RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam Date birthday) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userServiceImp.findUserByUsername(auth.getName()).getId();
        User user = userRepository.getOne(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit")
    public ModelAndView editUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        User user = userServiceImp.findUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profileEdit");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        User user = userServiceImp.findUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping("user/ban/{id}")
    public String ban(@PathVariable Long id) {
        userServiceImp.BanUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("user/unban/{id}")
    public String unban(@PathVariable Long id) {
        userServiceImp.UnbanUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/access-denied")
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error","Content Available Only for Admin!!!");
        modelAndView.addObject("advice","Press on the sad cat to go to the tours page");
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }



}
