package ru.yandex.practicum.filmorate.service.mparating;

import ru.yandex.practicum.filmorate.dto.mparating.MPARatingDto;

import java.util.List;

public interface MPARatingService {
    List<MPARatingDto> findAll();

    MPARatingDto findById(Long id);
}
