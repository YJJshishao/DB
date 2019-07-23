package com.example.health.util;
import  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {//生成密码

    public static String generatePassword(String password)
    {
        return new BCryptPasswordEncoder().encode(password);
    }
    public static void main(String[] args)
    {
        System.out.println(generatePassword("a9436644"));
    }
}
