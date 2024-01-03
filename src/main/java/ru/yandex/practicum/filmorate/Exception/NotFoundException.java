package ru.yandex.practicum.filmorate.Exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;

    public NotFoundException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
