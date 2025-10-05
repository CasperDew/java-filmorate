package ru.yandex.practicum.filmorate.repository.friends;

public interface FriendsRepository {
    void addFriend(Long userId, Long friendId);

    void removeFriend(Long userId, Long friendId);
}
