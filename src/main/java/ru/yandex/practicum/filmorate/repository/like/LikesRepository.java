package ru.yandex.practicum.filmorate.repository.like;

public interface LikesRepository {
    void addLike(Long userId, Long filmId);

    void removeLike(Long userId, Long filmId);
}
