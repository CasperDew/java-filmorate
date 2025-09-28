package ru.yandex.practicum.filmorate.storage.likes;

public interface LikeStorage {
    void addLike(Long filmId, Long userId);

    void removeLike(Long filmId, Long userId);

    int getLikes(Long filmId);

}
