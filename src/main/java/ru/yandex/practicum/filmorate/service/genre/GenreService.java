package ru.yandex.practicum.filmorate.service.genre;

import ru.yandex.practicum.filmorate.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

    GenreDto findById(Long id);
}
