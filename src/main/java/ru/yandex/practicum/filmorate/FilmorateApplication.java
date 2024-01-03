package ru.yandex.practicum.filmorate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmorateApplication implements CommandLineRunner {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(FilmorateApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("App is running on the address http://localhost:" + port);
    }
}
