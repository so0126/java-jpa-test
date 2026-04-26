package com.example.demo.common.exception;

public record ErrorResponse (
    String message,
    int status

){}
