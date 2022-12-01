package com.kata.cinema.base.util;

import com.kata.cinema.base.exceptions.NoChapterException;
import com.kata.cinema.base.exceptions.NoScoreException;
import com.kata.cinema.base.exceptions.ScoreAlreadyExistsException;
import com.kata.cinema.base.exceptions.WrongUserScoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class ExceptionRestController extends RuntimeException {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> errorResponse(final Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(Date.from(Instant.now()), e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler({NoChapterException.class,
            NoScoreException.class,
            ScoreAlreadyExistsException.class,
            WrongUserScoreException.class})
    public ResponseEntity<ErrorResponse> emptyResultResponse(final Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(Date.from(Instant.now()), e.getMessage(),
                HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
