package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmorateApplicationTests {

    FilmController filmController = new FilmController();
    UserController userController = new UserController();

    @Test
    void validateFilmNameIsEmpty() {
        Film film = new Film();
        film.setName("");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(10);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            filmController.addFilm(film);
        });
        assertTrue(exception.getMessage().contains("Название фильма"));
    }

    @Test
    void validateFilmDescriptionTooLong() {
        Film film = new Film();
        film.setName("Name");
        film.setDescription("a".repeat(201));
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(10);

        ValidationException exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film));
        assertTrue(exception.getMessage().contains("Описание не может превышать 200 символов"));
    }

    @Test
    void validateFilmIfReleaseDateTooEarly() {
        Film film = new Film();
        film.setName("Name");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(1895, 12, 27));
        film.setDuration(10);

        ValidationException exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film));
        assertTrue(exception.getMessage().contains("Дата релиза"));
    }

    @Test
    void validateFilmDurationZeroOrNegative() {
        Film film = new Film();
        film.setName("Name");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(0);

        ValidationException exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film));
        assertTrue(exception.getMessage().contains("Продолжительность"));
    }

    @Test
    void validateFilmPassIfAllOk() {
        Film film = new Film();
        film.setName("Name");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(10);

        assertDoesNotThrow(() -> filmController.addFilm(film));
    }

    @Test
    void shouldSetLoginAsNameIfNameIsBlank() {

        User user = new User();
        user.setEmail("user@example.com");
        user.setLogin("testUser");
        user.setName("");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        User result = userController.create(user);

        assertEquals("testUser", result.getName(), "Имя должно быть равно логину, если оно пустое");
    }


}
