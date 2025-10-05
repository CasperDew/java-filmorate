package ru.yandex.practicum.filmorate.repository.mparating;

import ru.yandex.practicum.filmorate.model.MPARating;

import java.util.List;
import java.util.Optional;

public interface MPARatingRepository {
    List<MPARating> findAll();

    Optional<MPARating> findById(Long id);
}
