package com.example.demo;

public class Main {
    public static void main(String[] args) throws Exception {
        SimpleIoc ioc = new SimpleIoc();
        ioc.scan("com.example.demo");

        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService.getInfo());
    }
}
