package com.kata.cinema.base.exceptions;

public class ScoreAlreadyExistsException extends RuntimeException {
    public ScoreAlreadyExistsException() {
        super("Оценка уже выставлена");
    }
}
