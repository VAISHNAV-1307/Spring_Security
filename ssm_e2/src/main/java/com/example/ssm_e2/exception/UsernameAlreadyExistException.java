package com.example.ssm_e2.exception;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException() {
        super("Username already exist");
    }
}
