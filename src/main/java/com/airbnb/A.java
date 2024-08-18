package com.airbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String enCodePassword = BCrypt.hashpw("testing", BCrypt.gensalt(10));
        System.out.println(enCodePassword);
    }
}
