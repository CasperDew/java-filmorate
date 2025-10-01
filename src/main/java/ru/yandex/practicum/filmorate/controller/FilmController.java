package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmsService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmsService filmsService;

    @GetMapping
    public List<Film> findAll() {
        log.info("Получен список фильмов");
        return filmsService.getAll();
    }

    @GetMapping("/{id}")
    public void getFilmById(@PathVariable Long id) {
        log.info("Получен фильм с id: {}", id);
        filmsService.getFilmById(id);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam(defaultValue = "10", required = false) Integer count) {
        log.info("Получено {} популярных фильмов (По умолчаную 10 шт)", count);
        return filmsService.getPopularFilms(count);
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Добавлен фильм: {}", film);
        return filmsService.create(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
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
        filmsService.delete(id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable Long id, @PathVariable Long userId) {
        log.info("Пользователь {} удалил лайк фильму {}", userId, id);
        filmsService.removeLike(id, userId);
    }
}
