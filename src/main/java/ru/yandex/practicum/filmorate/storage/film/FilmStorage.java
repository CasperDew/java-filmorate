package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    List<Film> getAll();

    void getById(Long id);

    Film create(Film film);

    Film update(Film film);

    void delete(long id);
}
