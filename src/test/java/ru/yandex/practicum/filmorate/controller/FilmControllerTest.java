package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.Exception.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmControllerTest {
    FilmController controller;

    @Test
    public void realiseDate_IsBeforeFirstMovie() {
        controller = new FilmController();
        Film film = Film.builder()
                .name("Valid name")
                .description("Valid description")
                .releaseDate(LocalDate.of(1495, 3, 12))
                .duration(120)
                .build();
        assertThrows(ValidateException.class, () -> controller.addFilms(film));
    }
}