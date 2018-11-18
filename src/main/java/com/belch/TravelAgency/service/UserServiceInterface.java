package com.belch.TravelAgency.service;

import com.belch.TravelAgency.entities.User;

public interface UserServiceInterface {

    void saveUser(User user);
    User findUserByUsername(String username);
    void BanUser(Long id);
    void UnbanUser(Long id);
    }
