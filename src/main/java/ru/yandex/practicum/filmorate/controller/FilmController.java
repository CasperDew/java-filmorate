package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.film.FilmDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.service.film.FilmsService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Validated
public class FilmController {
    private final FilmsService filmsService;

    @GetMapping
    public List<FilmDto> findAll() {
        log.info("Получен список фильмов");
        return filmsService.findAll();
    }

    @GetMapping("/{filmId}")
    public FilmDto findById(@PathVariable @Positive long filmId) {
        log.info("Получен фильм с id: {}", filmId);
        return filmsService.findById(filmId);
    }
    @GetMapping("/popular")
    public List<FilmDto> getPopularFilms(@RequestParam(defaultValue = "10", required = false) Integer count) {
        log.info("Получено {} популярных фильмов (По умолчаную 10 шт)", count);
        return filmsService.findFilmsWithTopLikes(count);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated
    public FilmDto create(@Valid @RequestBody NewFilmRequest film) {
        log.info("Добавлен фильм: {}", film);
        return filmsService.create(film);
    }

    @PutMapping
    @Validated
    public FilmDto updateFilm(@Valid @RequestBody UpdateFilmRequest film) {
        log.info("Обновлен фильм: {}", film);
        return filmsService.update(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Long id, @PathVariable Long userId) {
        log.info("Пользователь {} поставил лайк фильму {}", userId, id);
        filmsService.addLike(id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Удален фильм с id: {}", id);
        filmsService.deleteById(id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable Long id, @PathVariable Long userId) {
        log.info("Пользователь {} удалил лайк фильму {}", userId, id);
        filmsService.removeLike(id, userId);
    }
}
