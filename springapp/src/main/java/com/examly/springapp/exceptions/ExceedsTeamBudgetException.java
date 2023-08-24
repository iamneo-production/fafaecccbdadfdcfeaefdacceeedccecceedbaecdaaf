package com.examly.springapp.exceptions;

public class ExceedsTeamBudgetException extends RuntimeException {
    public ExceedsTeamBudgetException(String message) {
        super(message);
    }
}

