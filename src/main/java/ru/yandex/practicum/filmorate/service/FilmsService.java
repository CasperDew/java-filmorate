package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikeStorage;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FilmsService {
    private final UserService userService;
    private final FilmStorage filmStorage;
    private final LikeStorage likeStorage;

    @Autowired
    public FilmsService(UserService userService, FilmStorage filmStorage, LikeStorage likeStorage) {
        this.userService = userService;
        this.filmStorage = filmStorage;
        this.likeStorage = likeStorage;
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }

    public void getFilmById(Long id) {
        filmStorage.getById(id);
    }

    public Film create(Film film) {
        return filmStorage.create(film);
    }

    public Film update(Film film) {
        return filmStorage.update(film);
    }

    public void delete(long id) {
        filmStorage.delete(id);
    }

    public void addLike(Long filmId, Long userId) {
        getFilmById(filmId);
        userService.getUserById(userId);

        likeStorage.addLike(filmId, userId);
    }

    public void removeLike(Long filmId, Long userId) {
        getFilmById(filmId);
        likeStorage.removeLike(filmId, userId);
    }

    public List<Film> getPopularFilms(int count) {
        return filmStorage.getAll().stream()
                .sorted((f1, f2) -> Integer.compare(
                        getLikesCount((long) f2.getId()),
                        getLikesCount((long) f1.getId())
                ))
                .limit(count)
                .collect(Collectors.toList());
    }


    private int getLikesCount(Long filmId) {
        return likeStorage.getLikes(filmId);
    }

}
