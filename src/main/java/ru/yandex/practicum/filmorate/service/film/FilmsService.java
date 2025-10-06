package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.film.FilmDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmRequest;

import java.util.List;

public interface FilmsService {
    List<FilmDto> findAll();

    FilmDto findById(long id);

    FilmDto create(NewFilmRequest request);

    FilmDto update(UpdateFilmRequest request);

    void deleteById(Long id);

    void addLike(Long filmId, Long userId);

    void removeLike(Long filmId, Long userId);

    List<FilmDto> findFilmsWithTopLikes(int count);

}
