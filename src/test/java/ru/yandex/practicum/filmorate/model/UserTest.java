package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class UserTest {
    private Validator validator;
    private UserController controller;

    @BeforeEach
    public void startMethod() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        controller = new UserController();
    }

    @Test
    public void whenEmailIsInvalid_thenValidationFails() {
        User user = User.builder()
                .email("invalid-email")
                .login("ValidLogin")
                .birthday(LocalDate.of(1990, 1, 1))
                .build();
        Set<ConstraintViolation<User>> violations = validator.validateProperty(user, "email");
        assertFalse(violations.isEmpty());
        assertEquals("non standard writing of mail", violations.iterator().next().getMessage());
    }

    @Test
    public void whenLoginIsInvalid_thenValidationFails() {
        User user = User.builder()
                .email("valid.email@example.com")
                .login("")
                .birthday(LocalDate.of(1990, 1, 1))
                .build();
        controller.addUser(user);
        assertEquals(user.getLogin(), user.getName());
    }

    @Test
    public void whenBirthdayIsInTheFuture_thenValidationFails() {
        User user = User.builder()
                .email("valid.email@example.com")
                .login("ValidLogin")
                .birthday(LocalDate.now().plusDays(1))
                .build();
        Set<ConstraintViolation<User>> violations = validator.validateProperty(user, "birthday");
        assertFalse(violations.isEmpty());
        assertEquals("birth day musnt be in the future", violations.iterator().next().getMessage());
    }
}
