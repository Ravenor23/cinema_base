package com.kata.cinema.base.exceptions;

public class WrongUserScoreException extends RuntimeException {
    public WrongUserScoreException() {
        super("Deleting another user's score is not allowed");
    }
}
