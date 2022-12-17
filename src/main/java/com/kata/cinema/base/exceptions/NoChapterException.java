package com.kata.cinema.base.exceptions;

public class NoChapterException extends RuntimeException {

    public NoChapterException(Long id) {
        super(String.format("Chapter with id = %s does not exist", id));
    }
}
