package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static ru.yandex.practicum.filmorate.constant.UserConstant.REGEX_EMAIL;
import static ru.yandex.practicum.filmorate.constant.UserConstant.REGEX_LOGIN;

@Data
@Builder
public class User {
    private  Integer id;
    private  String name;
    @Email(regexp = REGEX_EMAIL, message = "non standard writing of mail")
    private  String email;
    @NotEmpty(message = "login musnt't be empty")
    @Pattern(regexp = REGEX_LOGIN, message = "login mustn't be empty")
    private  String login;
    @Past(message = "birth day musnt be in the future")
    private  LocalDate birthday;
}