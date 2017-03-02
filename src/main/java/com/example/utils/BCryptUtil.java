package com.example.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {
    public static void main(String[] args) {
        String pass = "";
        String result = BCrypt.hashpw(pass, BCrypt.gensalt());

        System.out.println(result);
    }
}
