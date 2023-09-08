package com.example.javaspring.controller;

public class CustomException extends RuntimeException {

    public CustomException(String s) {
        super(s);
    }
}
