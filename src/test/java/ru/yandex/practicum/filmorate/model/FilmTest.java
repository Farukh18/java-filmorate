package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilmTest {
    private Validator validator;

    @BeforeEach
    public void startMethod() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenNameIsBlank_thenValidationFails() {
        Film film = Film.builder()
                .name("")
                .description("Valid Description")
                .releaseDate(LocalDate.now())
                .duration(120)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validateProperty(film, "name");
        assertFalse(violations.isEmpty());
        assertEquals("string name must not be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void whenDescriptionIsInvalid_thenValidationFails() {
        Film film = Film.builder()
                .name("Valid Name")
                .description("")
                .releaseDate(LocalDate.now())
                .duration(120)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validateProperty(film, "description");
        assertFalse(violations.isEmpty());
        assertEquals("description max 200 symbols", violations.iterator().next().getMessage());
    }

    @Test
    public void whenDurationIsNegative_thenValidationFails() {
        Film film = Film.builder()
                .name("Valid Name")
                .description("Valid Description")
                .releaseDate(LocalDate.now())
                .duration(-1)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validateProperty(film, "duration");
        assertFalse(violations.isEmpty());
        assertEquals("duration must be positive", violations.iterator().next().getMessage());
    }
}
