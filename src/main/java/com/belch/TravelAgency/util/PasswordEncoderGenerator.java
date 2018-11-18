package com.belch.TravelAgency.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {

        String password_user = "12345";
        String password_aqua_len = "160297";
        String password_lida97 = "250697";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPasswordUser = passwordEncoder.encode(password_user);
        String hashedPasswordLen = passwordEncoder.encode(password_aqua_len);
        String hashedPasswordLid = passwordEncoder.encode(password_lida97);

        System.out.println(hashedPasswordUser + " " +hashedPasswordLen + " " +hashedPasswordLid);

    }

}
