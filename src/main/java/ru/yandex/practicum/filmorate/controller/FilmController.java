package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.Exception.NotFoundException;
import ru.yandex.practicum.filmorate.Exception.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.constant.FilmConstant.RELEASE_DATE;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    public final Map<Integer, Film> films;
    private Integer id = 0;

    public FilmController() {
        this.films = new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        log.info("current number of films in the program");
        return ResponseEntity.ok().body(new ArrayList<>(films.values()));
    }

    @PostMapping
    public ResponseEntity<Film> addFilms(@Valid @RequestBody Film film) {
        validateFilm(film);
        film.setId(++id);
        log.info("film is added");
        films.put(film.getId(), film);
        return ResponseEntity.ok(film);
    }


    @PutMapping
    public ResponseEntity<Film> updateFilm(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            log.info("film is updated: {} ", film);
            films.put(film.getId(), film);
        } else {
            throw new NotFoundException(String.format("Film with id `%d` not found.", film.getId()));
        }
        return ResponseEntity.ok(film);
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(RELEASE_DATE)) {
            log.error("A film added with a release earlier than 1895-12-28.");
            throw new ValidateException("A film added with a release earlier than 1895-12-28.");
        }
    }
}