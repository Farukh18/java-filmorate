package ru.yandex.practicum.filmorate.Exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    HttpStatus httpStatus;

    public NotFoundException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
