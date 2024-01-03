package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@Builder
public class Film {
    Integer id;
    @NotBlank(message = "string name must not be empty")
    String name;

    @Size(max = 200, min = 1, message = "description max 200 symbols")
    String description;

    LocalDate releaseDate;

    @Positive(message = "duration must be positive")
    Integer duration;
}
