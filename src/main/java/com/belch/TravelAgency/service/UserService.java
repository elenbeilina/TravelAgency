package com.belch.TravelAgency.service;

import com.belch.TravelAgency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.belch.TravelAgency.entities.User;
import com.belch.TravelAgency.entities.Role;
import com.belch.TravelAgency.repositories.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public void BanUser(Long id) {
       User user = userRepository.getOne(id);
       user.setActive(false);
       userRepository.save(user);
    }

    @Override
    public void UnbanUser(Long id) {
        User user = userRepository.getOne(id);
        user.setActive(true);
        userRepository.save(user);
    }


}
