package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.Exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    public final Map<Integer, User> users;
    private Integer id = 0;

    public UserController() {
        this.users = new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("all users in the program");
        return ResponseEntity.ok().body(new ArrayList<>(users.values()));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        validUser(user);
        log.info("user has been successfully added");
        user.setId(++id);
        users.put(user.getId(), user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        if (users.containsKey(user.getId())) {
            validUser(user);
            log.info("ussr has been updated{}", user);
            users.put(user.getId(), user);
        } else {
            log.error(String.format("user with id `%d` not found", user.getId()));
            throw new NotFoundException(String.format("user with id `%d` not found", user.getId()));
        }
        return ResponseEntity.ok().body(user);
    }

    private void validUser(User user) {
        String name = user.getName();
        if (name == null || name.isEmpty()) {
            log.warn("name set login");
            user.setName(user.getLogin());
        }
    }
}
