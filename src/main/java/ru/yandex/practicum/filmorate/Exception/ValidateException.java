package ru.yandex.practicum.filmorate.Exception;

import org.springframework.http.HttpStatus;

public class ValidateException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ValidateException(String message) {
        super(message);
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
