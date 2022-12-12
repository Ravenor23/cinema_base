package com.kata.cinema.base.exceptions;

public class NoScoreException extends RuntimeException {
    public NoScoreException(Long scoreId) {
        super(String.format("There is no score with id = %s", scoreId));
    }
}
