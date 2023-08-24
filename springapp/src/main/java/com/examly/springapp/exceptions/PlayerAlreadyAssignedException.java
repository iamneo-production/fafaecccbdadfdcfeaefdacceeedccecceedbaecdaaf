package com.examly.springapp.exceptions;

public class PlayerAlreadyAssignedException extends RuntimeException {
    public PlayerAlreadyAssignedException(String message) {
        super(message);
    }
}