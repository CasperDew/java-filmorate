package ru.yandex.practicum.filmorate.repository.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    List<Film> findAll();

    Optional<Film> findById(Long id);

    Film save(Film film);

    void update(Film film);

    void deleteById(Long id);

    List<Film> findTopPopularFilms(int count);
}
